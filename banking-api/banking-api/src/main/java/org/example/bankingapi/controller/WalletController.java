package org.example.bankingapi.controller;

import org.example.bankingapi.dto.*;
import org.example.bankingapi.entity.OneTimePass;
import org.example.bankingapi.service.UserService;
import org.example.bankingapi.service.WalletService;
import org.example.bankingapi.utils.EntityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bank")
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;

    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @GetMapping("/wallets")
    public List<WalletDto> getWallets() {
        return walletService.getAllWallets()
                .stream()
                .map(EntityUtil::toWalletDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.findAll()
                .stream()
                .map(EntityUtil::toUserDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/users/{username}")
    public WalletDto getWalletByUserId(@PathVariable("username") String username) {
        return EntityUtil.toWalletDto(walletService.getWalletByUserName(username));
    }

    @GetMapping("/generate/{cardId}")
    public ResponseEntity<OneTimePassDto> generateOneTimePassWithCardId(@PathVariable long cardId) {
        return new ResponseEntity<>(EntityUtil.toOneTimePassDto(walletService.generateOneTimePassWithCardId(cardId)), HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<OneTimePassDto> generateOneTimePass() {
        return new ResponseEntity<>(EntityUtil.toOneTimePassDto(walletService.generateOneTimePass()), HttpStatus.OK);
    }

    @GetMapping("/validate/{cardId}/{otpCode}")
    public ResponseEntity<Boolean> validateOneTimePass(@PathVariable long cardId, @PathVariable String otpCode) {
        return new ResponseEntity<>(walletService.validateOtpCode(cardId, otpCode), HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<WalletRecordDto> transactionDemo(@RequestBody TransactionDto transactionDto) {
        WalletRecordDto walletRecordDto = EntityUtil.toWalletRecordDto(walletService.transaction(transactionDto));
        return new ResponseEntity<>(walletRecordDto, HttpStatus.OK);
    }
}
