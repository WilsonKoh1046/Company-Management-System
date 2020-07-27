package com.example.company.service.accounting;

import com.example.company.model.accounting.Account;
import com.example.company.model.accounting.Transaction;
import com.example.company.model.accounting.TransactionMode;
import com.example.company.repository.accounting.AccountRepository;
import com.example.company.repository.accounting.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void createAccount(Account account, String mode) {
        accountRepository.save(toEntity(account, mode));
    }

    public List<Account> getAllAccounts() {
        List<Account> all_accounts = new ArrayList<>();
        accountRepository.findAll().forEach(all_accounts::add);
        return all_accounts;
    }

    public Account getAccountById(int id) {
        Optional<Account> targeted_account = accountRepository.findById(id);
        if (targeted_account.isEmpty()) {
            throw new NoSuchElementException();
        }
        return targeted_account.get();
    }

    private Account toEntity(Account account, String mode) {
        Account entity = new Account();
        entity.setName(account.getName());
        entity.setAmount(account.getAmount());
        entity.setTransactionMode(TransactionMode.toValue(mode));
        Optional<Transaction> targeted_transaction = transactionRepository.findById(account.getTransId());
        if (targeted_transaction.isEmpty()) {
            throw new NoSuchElementException();
        }
        entity.setTransId(account.getTransId());
        Transaction transaction = targeted_transaction.get();
        entity.setTransaction(transaction);
        return entity;
    }
}
