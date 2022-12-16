package com.iqbalfa.electronic.service.interfaces;

import com.iqbalfa.electronic.model.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction create(Transaction transaction) throws Exception;

    List<Transaction> list() throws Exception;

    Transaction getById(Long id) throws Exception;

    void delete(Long id) throws Exception;

    Transaction update(Long id, Transaction transaction) throws Exception;

}
