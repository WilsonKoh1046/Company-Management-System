package com.example.company.controller;

import com.example.company.service.CompanyFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/finance")
public class CompanyFinanceController {

    @Autowired
    private CompanyFinanceService companyFinanceService;

    public CompanyFinanceController(CompanyFinanceService companyFinanceService) {
        this.companyFinanceService = companyFinanceService;
    }

    @GetMapping("/getCompanyProfit")
    public int getCompanyProfit() {
        return companyFinanceService.getCompanyProfit();
    }
}
