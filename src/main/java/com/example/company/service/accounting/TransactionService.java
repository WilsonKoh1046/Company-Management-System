package com.example.company.service.accounting;

import com.example.company.model.accounting.Transaction;
import com.example.company.repository.accounting.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(toEntity(transaction));
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> all_transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(all_transactions::add);
        return all_transactions;
    }

    private Transaction toEntity(Transaction transaction) {
        Transaction entity = new Transaction();
        entity.setName(transaction.getName());
        entity.setEntry(new ArrayList<>());
        return entity;
    }
}
