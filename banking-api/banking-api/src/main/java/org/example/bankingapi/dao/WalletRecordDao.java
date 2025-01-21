package org.example.bankingapi.dao;

import org.example.bankingapi.entity.WalletRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalletRecordDao extends JpaRepository<WalletRecord, Long> {
    @Query("""
select w from WalletRecord w where w.senderWallet.cardId = :cardId or w.receiverWallet.cardId = :cardId
""")
    List<WalletRecord> findWalletRecordByWalletId(Long userId);
}
