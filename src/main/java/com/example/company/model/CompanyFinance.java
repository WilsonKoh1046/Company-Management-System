package com.example.company.model;

import org.springframework.stereotype.Component;

public class CompanyFinance {

    private int companyProfit;
    private int companyDebt;
    private int companyExpense;

    public CompanyFinance() {}

    public CompanyFinance(int companyProfit, int companyDebt, int companyExpense) {
        this.companyProfit = companyProfit;
        this.companyDebt = companyDebt;
        this.companyExpense = companyExpense;
    }

    public int getCompanyProfit() {
        return companyProfit;
    }

    public void setCompanyProfit(int companyProfit) {
        this.companyProfit = companyProfit;
    }

    public int getCompanyDebt() {
        return companyDebt;
    }

    public void setCompanyDebt(int companyDebt) {
        this.companyDebt = companyDebt;
    }

    public int getCompanyExpense() {
        return companyExpense;
    }

    public void setCompanyExpense(int companyExpense) {
        this.companyExpense = companyExpense;
    }
}
