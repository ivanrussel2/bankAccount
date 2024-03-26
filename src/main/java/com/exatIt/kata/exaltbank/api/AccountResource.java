package com.exatIt.kata.exaltbank.api;

import com.exatIt.kata.exaltbank.api.dto.AccountDto;
import com.exatIt.kata.exaltbank.api.dto.DepositAccountDto;
import com.exatIt.kata.exaltbank.api.dto.SavingAccountDto;
import com.exatIt.kata.exaltbank.api.dto.TransactionDto;
import com.exatIt.kata.exaltbank.api.mappers.IMapper;
import com.exatIt.kata.exaltbank.domain.account.AccountException;
import com.exatIt.kata.exaltbank.domain.account.AccountNotFoundException;
import com.exatIt.kata.exaltbank.domain.account.AccountQueries;
import com.exatIt.kata.exaltbank.domain.account.TransactionQueries;
import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.user.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

    private final IMapper mapper;
    private final AccountQueries accountQueries;
    private final TransactionQueries transactionQueries;

    public AccountResource(final IMapper mapper, final AccountQueries accountQueries, TransactionQueries transactionQueries) {
        this.mapper = mapper;
        this.accountQueries = accountQueries;
        this.transactionQueries = transactionQueries;
    }

    @PostMapping("/deposit")
    public ResponseEntity createDeposit(@RequestBody DepositAccountDto accountDto, HttpServletRequest request)
            throws UserNotFoundException {
        String requestURI = request.getRequestURI();
        String host = request.getRequestURL().toString().replace(requestURI, "");
        UUID accountNumber = accountQueries.create(mapper.dtoToDomain(accountDto));
        URI location = ServletUriComponentsBuilder
                .fromUriString(host)
                .path("/accounts/" + accountNumber)
                .build()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/saving")
    public ResponseEntity<SavingAccountDto> createSaving(@RequestBody SavingAccountDto accountDto, HttpServletRequest request)
            throws UserNotFoundException {
        String requestURI = request.getRequestURI();
        String host = request.getRequestURL().toString().replace(requestURI, "");
        UUID accountNumber = accountQueries.create(mapper.dtoToDomain(accountDto));
        URI location = ServletUriComponentsBuilder
                .fromUriString(host)
                .path("/accounts/" + accountNumber)
                .build()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> find(@PathVariable UUID accountNumber) {
        Account account = accountQueries.find(accountNumber);
        if (account instanceof DepositAccount depositAccount)
            return ResponseEntity.ok(mapper.domainToDto(depositAccount));
        else if (account instanceof SavingAccount savingAccount)
            return ResponseEntity.ok(mapper.domainToDto(savingAccount));
        else return ResponseEntity.noContent().build();
    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public ResponseEntity withdraw(@PathVariable(value = "accountNumber") UUID accountNumber,
                                   @PathVariable(value = "amount") double amount) {
        try {
            accountQueries.withdraw(accountNumber, amount);
        } catch (AccountException | AccountNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/transactions/{accountNumber}/{month}/{year}")
    public ResponseEntity getMonthlyTransactions(@PathVariable UUID accountNumber, @PathVariable int month,@PathVariable int year){
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59);
        List<TransactionDto> transactionDtos =this.transactionQueries
                .getMonthlyTransactions(accountNumber,startDate,endDate).stream()
                .map(transaction -> this.mapper.domainToDto(transaction))
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDtos);
    }

}
