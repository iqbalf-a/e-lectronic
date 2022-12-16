package com.iqbalfa.electronic.repository;

import com.iqbalfa.electronic.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select * from m_transaction mt order by mt.transaction_id", nativeQuery = true)
    List<Transaction> findAll();

    @Transactional
    @Modifying
    @Query(value = "delete from m_transaction mt where mt.transaction_id = ?1", nativeQuery = true)
    void deleteTransactionById(Long transactionId);

    @Query(value = "select * from m_transaction mt where mt.transaction_id = ?1", nativeQuery = true)
    Optional<Transaction> findByTransactionById(Long transactionId);





}
