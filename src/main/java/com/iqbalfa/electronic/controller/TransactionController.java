package com.iqbalfa.electronic.controller;

import com.iqbalfa.electronic.model.Transaction;
import com.iqbalfa.electronic.model.User;
import com.iqbalfa.electronic.model.request.TransactionRequest;
import com.iqbalfa.electronic.model.request.UserRequest;
import com.iqbalfa.electronic.model.response.SuccessResponse;
import com.iqbalfa.electronic.service.interfaces.ITransactionService;
import com.iqbalfa.electronic.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private ITransactionService transactionService;
    private ModelMapper modelMapper;

    public TransactionController(ITransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ResponseEntity getAllTransactions() throws Exception {
        List<Transaction> result = transactionService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all data", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getTransactionById(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        Transaction result = transactionService.getById(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get data", result));
    }

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody TransactionRequest transactionRequest) throws Exception {
        Transaction newTransaction = modelMapper.map(transactionRequest, Transaction.class);
        Transaction result = transactionService.create(newTransaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create transaction", result));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable String id) throws Exception {
        Long newId = Long.valueOf(id);
        transactionService.delete(newId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete transaction", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTransaction(@PathVariable String id, @RequestBody TransactionRequest transactionRequest) throws Exception {
        Transaction newTransaction = modelMapper.map(transactionRequest, Transaction.class);
        Long newId = Long.valueOf(id);
        Transaction result = transactionService.update(newId, newTransaction);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update category", result));
    }

}
