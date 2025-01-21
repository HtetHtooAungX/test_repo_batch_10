package org.example.bankingapi.utils;

import org.example.bankingapi.dto.OneTimePassDto;
import org.example.bankingapi.dto.UserDto;
import org.example.bankingapi.dto.WalletDto;
import org.example.bankingapi.dto.WalletRecordDto;
import org.example.bankingapi.entity.OneTimePass;
import org.example.bankingapi.entity.User;
import org.example.bankingapi.entity.Wallet;
import org.example.bankingapi.entity.WalletRecord;

import java.util.stream.Collectors;

public class EntityUtil {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                EntityUtil.toWalletDto(user.getWallet()));
        return userDto;
    }

    public static WalletDto toWalletDto(Wallet wallet) {
        return new WalletDto(wallet.getCardId(),
                wallet.getAmount(),
                wallet.getSentTransactions()
                        .stream()
                        .map(EntityUtil::toWalletRecordDto)
                        .collect(Collectors.toUnmodifiableList()),
                wallet.getReceivedTransactions()
                        .stream()
                        .map(EntityUtil::toWalletRecordDto)
                        .collect(Collectors.toUnmodifiableList()));
    }

    public static OneTimePassDto toOneTimePassDto(OneTimePass oneTimePass) {
        return new OneTimePassDto(
                oneTimePass.getOtpCode(),
                oneTimePass.getWallet().getCardId(),
                oneTimePass.getExpiresAt());
    }

    public static WalletRecordDto toWalletRecordDto(WalletRecord walletRecord) {
        return new WalletRecordDto(
                walletRecord.getId(),
                walletRecord.getSenderWallet().getCardId(),
                walletRecord.getReceiverWallet().getCardId(),
                walletRecord.getAmount(),
                walletRecord.getCreatedAt()
        );
    }
}
