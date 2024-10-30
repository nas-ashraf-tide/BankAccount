package co.tide.businessservices.integrations;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Transaction {

    private Integer amount;
    private OffsetDateTime transactionDate;



}
