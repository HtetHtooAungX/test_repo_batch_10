package org.example.bankingapi.dao;

import org.example.bankingapi.entity.OneTimePass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OneTimePassDao extends JpaRepository<OneTimePass, Integer> {
    @Query("""
select o from OneTimePass o where o.wallet.cardId = ?1 and o.expiresAt > current_timestamp
""")
    Optional<OneTimePass> findOneTimePassByWalletId(long walletId);

    @Query("""
select o from OneTimePass o where o.wallet.cardId = ?1 and o.otpCode = ?2 and o.isUsed = false
""")
    Optional<OneTimePass> findOneTimePassByWalletIdAAndAndOtpCodeAndIsUsed(long walletId, String otpCode);
}
