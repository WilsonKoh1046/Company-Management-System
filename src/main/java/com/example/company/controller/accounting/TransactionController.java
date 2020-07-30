package com.example.company.controller.accounting;

import com.example.company.model.accounting.Transaction;
import com.example.company.service.accounting.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
        return new ResponseEntity<>("Transaction Created!", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> all_transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(all_transactions, HttpStatus.OK);
    }

    @GetMapping("/check-if-balance/{id}")
    public ResponseEntity<?> checkIfBalance(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(transactionService.checkIfBalance(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
