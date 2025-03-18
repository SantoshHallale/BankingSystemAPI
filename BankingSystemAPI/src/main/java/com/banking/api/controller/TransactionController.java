package com.banking.api.controller;

import com.banking.api.entity.Transaction;
import com.banking.api.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam Long accountId,@RequestParam BigDecimal amount){
        return ResponseEntity.ok(transactionService.deposit(accountId,amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam Long accountId,@RequestParam BigDecimal amount){
        return ResponseEntity.ok(transactionService.withdraw(accountId,amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam Long fromAccountId,@RequestParam Long toAccountId,@RequestParam BigDecimal amount){
        return ResponseEntity.ok(transactionService.transfer(fromAccountId,toAccountId,amount));
    }
}
