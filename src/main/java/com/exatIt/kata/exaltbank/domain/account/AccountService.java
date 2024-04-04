package com.exatIt.kata.exaltbank.domain.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.domain.user.UserNotFoundException;
import com.exatIt.kata.exaltbank.persistence.account.AccountGateway;
import com.exatIt.kata.exaltbank.persistence.account.TransactionGateway;
import com.exatIt.kata.exaltbank.persistence.user.UserGateway;
import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService implements AccountQueries {

    private final AccountGateway accountGateway;
    private final UserGateway userGateway;
    private final TransactionGateway loggingGateway;

    public AccountService(final AccountGateway accountGateway,
                          UserGateway userGateway,
                          TransactionGateway loggingGateway) {
        this.accountGateway = accountGateway;
        this.userGateway = userGateway;
        this.loggingGateway = loggingGateway;
    }

    @Override
    public Account find(UUID accountNumber) {
        return accountGateway.find(accountNumber);
    }

    @Override
    public UUID create(DepositAccount newAccount) throws UserNotFoundException {
        User user = userGateway.findById(newAccount.getOwner().getId());
        if(user== null)
            throw new UserNotFoundException("User not found !");
        return accountGateway.save(newAccount);
    }

    @Override
    public UUID create(SavingAccount newAccount) throws UserNotFoundException {
        User user = userGateway.findById(newAccount.getOwner().getId());
        if(user== null)
            throw new UserNotFoundException("User not found !");
        newAccount.setOverdraftAuthorization(false);
        return accountGateway.save(newAccount);
    }

    @Override
    public boolean withdraw(UUID accountNumber, double amount) throws AccountException,AccountNotFoundException {
        Account account = accountGateway.find(accountNumber);
        if(account==null)
            throw new AccountNotFoundException("Account does not exist");
        if (account instanceof DepositAccount depositAccount) {
           return withdrawDepositAccount(depositAccount, amount);
        } else if (account instanceof SavingAccount savingAccount) {
          return withdrawSavingAccount(savingAccount, amount);
        }
        return false;
    }

    private boolean withdrawDepositAccount(DepositAccount account, double amount) throws AccountException {
        double currentBalance = account.getBalance();
        if ((account.isOverdraftAuthorization() && (currentBalance + account.getOverDraftLimit() >= amount)) ||
             !account.isOverdraftAuthorization() && currentBalance >= amount) {
            account.setBalance(currentBalance - amount);
            accountGateway.updateDeposit(account);
            loggingGateway.saveAccountTransactions(account,AccountTransactionEnum.WITHDRAW,amount);
            return true;
        } else {
            throw new AccountException("your balance is not enough");
        }
    }

    private boolean withdrawSavingAccount(SavingAccount account, double amount) throws AccountException {
        if (account.getBalance() >= amount) {
            double currentBalance = account.getBalance();
            account.setBalance(currentBalance - amount);
            accountGateway.updateSaving(account);
            loggingGateway.saveAccountTransactions(account,AccountTransactionEnum.WITHDRAW,amount);
            return true;
        } else {
            throw new AccountException("your balance is not enough");
        }
    }

    @Override
    public boolean deposit(UUID accountNumber, double amount) throws AccountException {
        Account account = accountGateway.find(accountNumber);
        if (account != null) {
            if(account instanceof DepositAccount depositAccount){
                return depositOnDepositAccount(depositAccount,amount);
            }else if(account instanceof SavingAccount savingAccount){
                return depositOnSavingAccount(savingAccount,amount);
            }else{
                return false;
            }
        } else {
            throw new AccountException("Account not found");
        }
    }
    private boolean depositOnSavingAccount(SavingAccount account, double amount) throws AccountException {
        double currentBalance = account.getBalance();
        if(currentBalance + amount > account.getDepositLimit()){
            throw new AccountException("your balance will exceed the limit authorised");
        }else{
            account.setBalance(account.getBalance() + amount);
            accountGateway.updateSaving(account);
            loggingGateway.saveAccountTransactions(account,AccountTransactionEnum.DEPOSIT,amount);
        }
        return true;
    }

    private boolean depositOnDepositAccount(DepositAccount account, double amount){
        double currentBalance = account.getBalance();
        account.setBalance(account.getBalance() + amount);
        accountGateway.updateDeposit(account);
        loggingGateway.saveAccountTransactions(account,AccountTransactionEnum.DEPOSIT,amount);
        return true;
    }

    @Override
    public List<Account> list() {
        return accountGateway.list();
    }
}
