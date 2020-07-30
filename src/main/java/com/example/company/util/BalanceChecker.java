package com.example.company.util;

import com.example.company.model.accounting.Account;
import com.example.company.model.accounting.Transaction;
import com.example.company.model.accounting.TransactionMode;

import java.util.ArrayList;
import java.util.List;

public class BalanceChecker {

    public static Boolean check(Transaction transaction) {
        List<Account> debit = new ArrayList<>();
        List<Account> credit = new ArrayList<>();
        for (Account account: transaction.getEntry()) {
            if (TransactionMode.toString(account.getTransactionMode()).equals("debit")) {
                debit.add(account);
            } else {
                credit.add(account);
            }
        }
        return addAmount(debit) == addAmount(credit);
    }

    private static double addAmount(List<Account> accounts) {
        if (accounts.size() == 0) {
            return 0;
        }
        double sum = accounts.remove(0).getAmount();
        return sum + addAmount(accounts);
    }
}
