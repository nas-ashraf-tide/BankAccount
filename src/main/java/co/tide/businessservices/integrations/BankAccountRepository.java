package co.tide.businessservices.integrations;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository {

    Optional<BankAccount> fetchAccount(UUID accountId);

    BankAccount saveAccount(BankAccount account);
}
