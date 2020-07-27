package com.example.company.model.accounting;

public enum TransactionMode {
    DEBIT,
    CREDIT;

    public static TransactionMode toValue(String mode) {
        if (mode.equals("debit")) {
            return DEBIT;
        } else if (mode.equals("credit")) {
            return CREDIT;
        }
        return null;
    }

    public static String toString(TransactionMode transactionMode) {
        if (transactionMode == TransactionMode.DEBIT) {
            return "debit";
        } else if (transactionMode == TransactionMode.CREDIT) {
            return "credit";
        }
        return null;
    }
}
