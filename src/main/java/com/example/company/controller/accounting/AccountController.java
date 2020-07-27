package com.example.company.controller.accounting;

import com.example.company.model.accounting.Account;
import com.example.company.service.accounting.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create/mode/{mode}")
    public ResponseEntity<?> createAccount(@RequestBody Account account, @PathVariable("mode") String mode) {
        accountService.createAccount(account, mode);
        return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        List<Account> all_accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(all_accounts, HttpStatus.OK);
    }
}
