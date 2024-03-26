package com.exatIt.kata.exaltbank.domain.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.persistence.account.TransactionGateway;
import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private TransactionGateway transactionGateway;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMonthlyTransactions() {
        UUID accountNumber = UUID.randomUUID();
        LocalDateTime startDate = LocalDateTime.of(2024, 3, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 3, 31, 23, 59);

        // Création de transactions fictives pour le test
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(null,UUID.randomUUID(), AccountTransactionEnum.DEPOSIT,
                100.0, LocalDateTime.of(2024, 3, 5, 12, 0)));
        expectedTransactions.add(new Transaction(null,UUID.randomUUID(),AccountTransactionEnum.DEPOSIT,
                150.0, LocalDateTime.of(2024, 3, 15, 10, 0)));

        // Configuration du comportement du mock pour retourner les transactions fictives
        when(transactionGateway.getMonthlyTransactions(accountNumber, startDate, endDate)).thenReturn(expectedTransactions);

        // Appel de la méthode à tester
        List<Transaction> actualTransactions = transactionService.getMonthlyTransactions(accountNumber, startDate, endDate);

        // Vérification du résultat
        assertEquals(expectedTransactions.size(), actualTransactions.size());
        for (int i = 0; i < expectedTransactions.size(); i++) {
            assertEquals(expectedTransactions.get(i), actualTransactions.get(i));
        }
    }

}
