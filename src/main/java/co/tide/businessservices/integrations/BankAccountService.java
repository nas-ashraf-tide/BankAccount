package co.tide.businessservices.integrations;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountStatsPublisher accountStatsPublisher;

    public BankAccountService(BankAccountRepository bankAccountRepository, AccountStatsPublisher accountStatsPublisher) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountStatsPublisher = accountStatsPublisher;
    }


    public BankAccount deposit(UUID accountId, Integer amount) {
        BankAccount bankAccount = bankAccountRepository.fetchAccount(accountId)
                .orElseThrow(AccountDoesNotExistException::new);

        List<Integer> transactions  = bankAccount.getTransactions();
        transactions.add(amount);

        Integer newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);

        bankAccountRepository.saveAccount(bankAccount);

        int largestTransactionAmount = transactions.stream()
                .max(Integer::compareTo)
                .get();
        accountStatsPublisher.publishLargestTransaction(OffsetDateTime.now(), accountId, largestTransactionAmount);

        return bankAccount;
    }


    public BankAccount withdraw(UUID accountId, Integer amount) {
        BankAccount bankAccount = bankAccountRepository.fetchAccount(accountId)
                .orElseThrow(AccountDoesNotExistException::new);

        bankAccount.getTransactions().add(amount);
        Integer newBalance = bankAccount.getBalance() - amount;

        if (bankAccount.getAccountHolder().getCountry() == CountryCode.UK) {
            if (newBalance < -50) {
                throw new OverdraftExceededException();
            }
        }

        if (bankAccount.getAccountHolder().getCountry() == CountryCode.INDIA) {
            if (newBalance < -100) {
                throw new OverdraftExceededException();
            }
        }

        bankAccount.setBalance(newBalance);
        bankAccountRepository.saveAccount(bankAccount);

        return bankAccount;
    }
}
