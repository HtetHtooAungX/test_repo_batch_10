package org.example.bankingapi.service;

import org.example.bankingapi.dao.OneTimePassDao;
import org.example.bankingapi.dao.WalletDao;
import org.example.bankingapi.dao.WalletRecordDao;
import org.example.bankingapi.dto.TransactionDto;
import org.example.bankingapi.entity.OneTimePass;
import org.example.bankingapi.entity.Wallet;
import org.example.bankingapi.entity.WalletRecord;
import org.example.bankingapi.exception.IncorrectOtpCodeException;
import org.example.bankingapi.exception.InsufficientBalanceException;
import org.example.bankingapi.exception.WalletNotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {
    private final WalletDao walletDao;
    private final OneTimePassDao oneTimePassDao;
    private final WalletRecordDao walletRecordDao;

    public WalletService(WalletDao walletDao, OneTimePassDao oneTimePassDao, WalletRecordDao walletRecordDao) {
        this.walletDao = walletDao;
        this.oneTimePassDao = oneTimePassDao;
        this.walletRecordDao = walletRecordDao;
    }

    public List<Wallet> getAllWallets() {
        return walletDao.findAll();
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Wallet getWalletByUserName(String username) {
        return walletDao.findByUserName(username).orElse(null);
    }

    private String generateOtpCode() {
        return String.valueOf(new SecureRandom().nextInt(1, 100000)+100000);
    }

    public OneTimePass generateOneTimePass() {
        String username = getCurrentUser();
        Wallet wallet = getWalletByUserName(username);
        OneTimePass otp = oneTimePassDao.findOneTimePassByWalletId(wallet.getCardId()).orElse(null);
        if (otp == null) {
            String otpCode = generateOtpCode();
            OneTimePass newOneTimePass = new OneTimePass(
                    otpCode,
                    wallet,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(1),
                    false
            );
            otp = oneTimePassDao.save(newOneTimePass);
        }
        return otp;
    }

    @Secured("ROLE_ADMIN")
    public OneTimePass generateOneTimePassWithCardId(long cardId) {
        if (walletDao.existsById(cardId)) {
            OneTimePass otp = oneTimePassDao.findOneTimePassByWalletId(cardId).orElse(null);
            if (otp != null) {
                return otp;
            } else {
                Wallet wallet = walletDao.findById(cardId).get();
                String otpCode = generateOtpCode();
                OneTimePass oneTimePass = new OneTimePass(
                        otpCode,
                        wallet,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(1),
                        false);
                return oneTimePassDao.save(oneTimePass);
            }
        } else {
            throw new WalletNotFoundException();
        }
    }

    public boolean validateOtpCode(long cardId, String otpCode) {
        if (walletDao.existsById(cardId)) {
            OneTimePass otp = oneTimePassDao.findOneTimePassByWalletIdAAndAndOtpCodeAndIsUsed(cardId,otpCode).orElse(null);
            if (otp != null) {
                otp.setOtpId(otp.getOtpId());
                otp.setUsed(true);
                oneTimePassDao.save(otp);
                return true;
            }
            return false;
        } else {
            throw new WalletNotFoundException();
        }
    }

    @Transactional
    public WalletRecord transaction(TransactionDto transactionDto) {
        String rname = SecurityContextHolder.getContext().getAuthentication().getName();
        if (walletDao.existsById(transactionDto.getSenderCardId())) {
            Wallet senderWallet = walletDao.findById(transactionDto.getSenderCardId()).get();
            Wallet receiverWallet = walletDao.findByUserName(rname).get();

            if (validateOtpCode(transactionDto.getSenderCardId(),transactionDto.getOtpCode())) {
                if (senderWallet.getAmount() > transactionDto.getAmount()) {
                    senderWallet.setAmount(senderWallet.getAmount() - transactionDto.getAmount());
                    receiverWallet.setAmount(receiverWallet.getAmount() + transactionDto.getAmount());

                    senderWallet.setCardId(transactionDto.getSenderCardId());
                    receiverWallet.setCardId(receiverWallet.getCardId());
                    WalletRecord walletRecord = new WalletRecord(transactionDto.getAmount(), LocalDateTime.now());

                    senderWallet.addSentTransaction(walletRecord);
                    receiverWallet.addReceivedTransaction(walletRecord);

                    return walletRecordDao.save(walletRecord);
                } else {
                    throw new InsufficientBalanceException();
                }
            } else {
                throw new IncorrectOtpCodeException();
            }
        } else {
            throw new WalletNotFoundException();
        }
    }
}
