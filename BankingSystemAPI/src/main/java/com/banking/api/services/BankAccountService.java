package com.banking.api.services;

import com.banking.api.entity.BankAccount;
import com.banking.api.entity.User;
import com.banking.api.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(BankAccount bankAccount) {
        if (bankAccount.getBalance()==null){
            bankAccount.setBalance(BigDecimal.valueOf(0.0));
        }
        return bankAccountRepository.save(bankAccount);
    }

    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    public List<BankAccount> getBankAccountsForUser(User user) {
        return bankAccountRepository.findByUser(user);
    }

    public BankAccount updateBankAccount(Long accountId, BankAccount updateBankAccount) {
        BankAccount existingBankAccount=bankAccountRepository.findById(accountId).orElse(null);
        if (existingBankAccount!=null){
            existingBankAccount.setAccountHolderName(updateBankAccount.getAccountHolderName());
            existingBankAccount.setBalance(updateBankAccount.getBalance());
            return bankAccountRepository.save(existingBankAccount);
        }
        return null;
    }

    public void deleteBankAccount(Long accountId) {
        bankAccountRepository.deleteById(accountId);
    }
}
