package org.example.bankingapi.dao;

import org.example.bankingapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WalletDao extends JpaRepository<Wallet, Long> {

    @Query("""
select w from Wallet w where w.user.username =:username
""")
    Optional<Wallet> findByUserName(String username);

    Optional<Wallet> findByCardId(Long id);
}
