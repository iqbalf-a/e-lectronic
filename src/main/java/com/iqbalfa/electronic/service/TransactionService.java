package com.iqbalfa.electronic.service;

import com.iqbalfa.electronic.exception.EntityExistException;
import com.iqbalfa.electronic.exception.NotFoundException;
import com.iqbalfa.electronic.model.Product;
import com.iqbalfa.electronic.model.Transaction;
import com.iqbalfa.electronic.model.User;
import com.iqbalfa.electronic.repository.ITransactionRepository;
import com.iqbalfa.electronic.repository.IUserRepository;
import com.iqbalfa.electronic.service.interfaces.IProductService;
import com.iqbalfa.electronic.service.interfaces.ITransactionService;
import com.iqbalfa.electronic.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    private ITransactionRepository transactionRepository;
    private IUserService userService;
    private IProductService productService;

    public TransactionService(ITransactionRepository transactionRepository, IUserService userService, IProductService productService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Transaction create(Transaction transaction) {
        try {
            User user = userService.getById(transaction.getUser().getUserId());
            Product product = productService.getById(transaction.getProduct().getProductId());
            transaction.setUser(user);
            transaction.setProduct(product);
            Transaction newTransaction = transactionRepository.save(transaction);
            return newTransaction;
        } catch (Exception e) {
            throw new EntityExistException();
        }
    }

    @Override
    public List<Transaction> list() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }

    @Override
    public Transaction getById(Long id) throws Exception {
        Optional<Transaction> existingTransaction = transactionRepository.findByTransactionById(id);
        if (existingTransaction.isEmpty()) {
            throw new NotFoundException("Transaction with id " + id + " not found");
        }
        return existingTransaction.get();
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Transaction> existingTransaction = transactionRepository.findByTransactionById(id);
        if (existingTransaction.isEmpty()) {
            throw new NotFoundException("Transaction with id " + id + " not found");
        }
        transactionRepository.deleteTransactionById(id);
    }

    @Override
    public Transaction update(Long id, Transaction transaction) throws Exception {
        Optional<Transaction> existingTransaction = transactionRepository.findByTransactionById(id);
        if (existingTransaction.isEmpty()) {
            throw new NotFoundException("Transaction with id " + id + " not found");
        }
        transaction.setTransactionId(id);
        Transaction newTransaction = transactionRepository.save(transaction);
        return newTransaction;
    }

}
