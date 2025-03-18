package com.banking.api.controller;

import com.banking.api.entity.BankAccount;
import com.banking.api.entity.User;
import com.banking.api.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(bankAccount);
    }

    @GetMapping("/{accountNumber}")
    public Optional<BankAccount> getBankAccountByNumber(@PathVariable String accountNumber) {
        return bankAccountService.findByAccountNumber(accountNumber);
    }

    @GetMapping("/user/{userId}")
    public List<BankAccount> getBankAccountsForUser(@PathVariable Long userId) {
        User user=new User();
        user.setId(userId);
        return bankAccountService.getBankAccountsForUser(user);
    }

    @PutMapping("/{accountId}")
    public BankAccount updateBankAccount(@PathVariable Long accountId, @RequestBody BankAccount updateBankAccount) {
        return bankAccountService.updateBankAccount(accountId, updateBankAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Long accountId) {
        bankAccountService.deleteBankAccount(accountId);
        return ResponseEntity.ok().build();
    }

}