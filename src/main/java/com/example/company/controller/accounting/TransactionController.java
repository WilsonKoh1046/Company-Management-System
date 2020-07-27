package com.example.company.controller.accounting;

import com.example.company.model.accounting.Transaction;
import com.example.company.service.accounting.TransactionService;
import com.example.company.util.BalanceChecker;
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

    @Autowired
    private BalanceChecker balanceChecker;

    public TransactionController(TransactionService transactionService, BalanceChecker balanceChecker) {
        this.transactionService = transactionService;
        this.balanceChecker = balanceChecker;
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
            return new ResponseEntity<>(balanceChecker.check(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
