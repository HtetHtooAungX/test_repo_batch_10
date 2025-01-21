package org.example.bankingapi;

import lombok.RequiredArgsConstructor;
import org.example.bankingapi.dao.UserDao;
import org.example.bankingapi.dao.WalletDao;
import org.example.bankingapi.dao.WalletRecordDao;
import org.example.bankingapi.entity.Role;
import org.example.bankingapi.entity.User;
import org.example.bankingapi.entity.Wallet;
import org.example.bankingapi.entity.WalletRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class BankingApiApplication {
    private final UserDao userDao;
    private final WalletDao walletDao;
    private final WalletRecordDao walletRecordDao;
    private final PasswordEncoder passwordEncoder;

    public BankingApiApplication(UserDao userDao, WalletDao walletDao, WalletRecordDao walletRecordDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.walletDao = walletDao;
        this.walletRecordDao = walletRecordDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Transactional
    @Profile("dev")
    public ApplicationRunner runner() {
        return args -> {
//            User user1 = new User("John Doe", "john", "johndoe@gmail.com", passwordEncoder.encode("12345"));
//            User user2 = new User("Mary Shelly","mary","maryshelly@gmail.com", passwordEncoder.encode("12345"));
//            Role role1 = new Role();
//            role1.setName("ROLE_USER");
//            Role role2 = new Role();
//            role2.setName("ROLE_ADMIN");
//            user1.addRole(role1);
//            user2.addRole(role2);
//
//            Wallet wallet1 = new Wallet();
//            wallet1.setAmount(1000);
//            Wallet wallet2 = new Wallet();
//            wallet2.setAmount(2000);
//
//            user1.addWallet(wallet1);
//            user2.addWallet(wallet2);
//
//
//            userDao.save(user1);
//            userDao.save(user2);

//            WalletRecord wr1 = new WalletRecord(500, LocalDateTime.now());
//            WalletRecord wr2 = new WalletRecord(300, LocalDateTime.now());
//
//            Wallet w1 = walletDao.findByCardId(1L).get();
//            Wallet w2 = walletDao.findByCardId(2L).get();
//
//            w1.addSentTransaction(wr2);
//            w2.addSentTransaction(wr1);
//
//            w1.addReceivedTransaction(wr1);
//            w2.addReceivedTransaction(wr2);
//
//            walletRecordDao.save(wr1);
//            walletRecordDao.save(wr2);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BankingApiApplication.class, args);
    }

}
