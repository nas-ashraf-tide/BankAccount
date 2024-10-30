package co.tide.businessservices.integrations;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface AccountStatsPublisher {
    void publishLargestTransaction(OffsetDateTime transactionDate, UUID accountId, int largestTransactionAmount);
}
