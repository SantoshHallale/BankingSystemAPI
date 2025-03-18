package com.banking.api.services;

import com.banking.api.entity.BankAccount;
import com.banking.api.entity.Transaction;
import com.banking.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class TransactionService {
    @Autowired
    private  BankAccountService bankAccountService;
    @Autowired
    private  TransactionRepository transactionRepository;

    public Transaction deposit(Long accountId, BigDecimal amount) {
        Optional<BankAccount> optionalAccount = bankAccountService.findByAccountNumber(accountId.toString());
        if (optionalAccount.isPresent()) {
            BankAccount account = optionalAccount.get();
            account.setBalance(account.getBalance().add(amount));
            bankAccountService.updateBankAccount(account.getId(), account);

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setTransactionType("DEPOSIT");
            transaction.setFromAccount(null);
            transaction.setToAccount(account);
            transaction.setTimestamp(LocalDateTime.now());
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public Transaction withdraw(Long accountId, BigDecimal amount) {
        Optional<BankAccount> optionalAccount = bankAccountService.findByAccountNumber(accountId.toString());
        if (optionalAccount.isPresent()) {
            BankAccount account = optionalAccount.get();
            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
                bankAccountService.updateBankAccount(account.getId(), account);

                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setTransactionType("WITHDRAW");
                transaction.setFromAccount(account);
                transaction.setToAccount(null);
                transaction.setTimestamp(LocalDateTime.now());
                return transactionRepository.save(transaction);
            }
        }
        return null;
    }

    public Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Optional<BankAccount> fromOptional = bankAccountService.findByAccountNumber(fromAccountId.toString());
        Optional<BankAccount> toOptional = bankAccountService.findByAccountNumber(toAccountId.toString());

        if (fromOptional.isPresent() && toOptional.isPresent()) {
            BankAccount fromAccount = fromOptional.get();
            BankAccount toAccount = toOptional.get();

            if (fromAccount.getBalance().compareTo(amount) >= 0) {
                fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
                toAccount.setBalance(toAccount.getBalance().add(amount));

                bankAccountService.updateBankAccount(fromAccount.getId(), fromAccount);
                bankAccountService.updateBankAccount(toAccount.getId(), toAccount);

                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setTransactionType("TRANSFER");
                transaction.setTimestamp(LocalDateTime.now());
                transaction.setFromAccount(fromAccount);
                transaction.setToAccount(toAccount);
                return transactionRepository.save(transaction);
            }
        }
        return null;
    }

}
