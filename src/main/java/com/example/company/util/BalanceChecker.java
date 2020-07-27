package com.example.company.util;

import com.example.company.model.accounting.Account;
import com.example.company.model.accounting.Transaction;
import com.example.company.model.accounting.TransactionMode;
import com.example.company.repository.accounting.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class BalanceChecker {

    @Autowired
    private TransactionRepository transactionRepository;

    public BalanceChecker(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Boolean check(int transactionId) {
        List<Account> debit = new ArrayList<>();
        List<Account> credit = new ArrayList<>();
        Optional<Transaction> targeted_transaction = transactionRepository.findById(transactionId);
        if (targeted_transaction.isEmpty()) {
            throw new NoSuchElementException("Transaction not found");
        }
        Transaction transaction = targeted_transaction.get();
        for (Account account: transaction.getEntry()) {
            if (TransactionMode.toString(account.getTransactionMode()).equals("debit")) {
                debit.add(account);
            } else {
                credit.add(account);
            }
        }
        return addAmount(debit) == addAmount(credit);
    }

    private double addAmount(List<Account> accounts) {
        if (accounts.size() == 0) {
            return 0;
        }
        double sum = accounts.remove(0).getAmount();
        return sum + addAmount(accounts);
    }
}
