package org.example.bankingapi.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private long id;
    private String name;
    private String username;
    private String email;
    private WalletDto wallet;

    public UserDto(long id, String name, String username, String email, WalletDto wallet) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.wallet = wallet;
    }

    public WalletDto getWallet() {
        return wallet;
    }

    public void setWallet(WalletDto wallet) {
        this.wallet = wallet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}