package com.exatIt.kata.exaltbank.domain.account;

import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.domain.user.UserNotFoundException;
import com.exatIt.kata.exaltbank.persistence.account.AccountGateway;
import com.exatIt.kata.exaltbank.persistence.account.TransactionGateway;
import com.exatIt.kata.exaltbank.persistence.user.UserGateway;
import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountGateway accountGateway;

    @Mock
    private UserGateway userGateway;

    @Mock
    private TransactionGateway loggingGateway;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ShouldCreateDepositAccount() throws UserNotFoundException {
        User user = new User();
        user.setId(new Random().nextLong());
        DepositAccount depositAccount = new DepositAccount(UUID.randomUUID(), 1000.0, LocalDateTime.now(),
                user, null, 500.0, true);

        when(userGateway.findById(user.getId())).thenReturn(user);
        when(accountGateway.save(depositAccount)).thenReturn(UUID.randomUUID());

        UUID accountId = accountService.create(depositAccount);
        assertNotNull(accountId);
        verify(userGateway, times(1)).findById(user.getId());
        verify(accountGateway, times(1)).save(depositAccount);
    }

    @Test
    public void shouldThrowErrorWhenCreateDepositAccountWithUserNotFound() {
        DepositAccount depositAccount = new DepositAccount(UUID.randomUUID(), 1000.0, LocalDateTime.now(),
                new User(), null, 500.0, true);

        when(userGateway.findById(any(Long.class))).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            accountService.create(depositAccount);
        });
        verify(accountGateway, never()).save(depositAccount);
    }

    @Test
    public void shouldWithdrawDepositAccountWhenBalanceIsEnough() throws AccountException, AccountNotFoundException {
        DepositAccount depositAccount = new DepositAccount(UUID.randomUUID(), 1000.0,
                LocalDateTime.now(), new User(), null, 500.0, true);

        when(accountGateway.find(depositAccount.getAccountNumber())).thenReturn(depositAccount);

        boolean result = accountService.withdraw(depositAccount.getAccountNumber(), 500.0);
        assertTrue(result);
        verify(accountGateway, times(1)).find(depositAccount.getAccountNumber());
        verify(accountGateway, times(1)).updateDeposit(depositAccount);
        verify(loggingGateway, times(1)).saveAccountTransactions(depositAccount,
                AccountTransactionEnum.WITHDRAW, 500.0);
    }

    @Test
    public void shouldThrowExceptionWhenWithdrawDepositAccountWhithInsufficientBalance() {
        DepositAccount depositAccount = new DepositAccount(UUID.randomUUID(), 100.0, LocalDateTime.now(),
                new User(), null, 500.0, true);

        when(accountGateway.find(depositAccount.getAccountNumber())).thenReturn(depositAccount);

        assertThrows(AccountException.class, () -> {
            accountService.withdraw(depositAccount.getAccountNumber(), 2000.0);
        });
        verify(accountGateway, times(1)).find(depositAccount.getAccountNumber());
        verify(accountGateway, never()).updateDeposit(depositAccount);
        verify(loggingGateway, never()).saveAccountTransactions(depositAccount, AccountTransactionEnum.WITHDRAW, 200.0);
    }

}
