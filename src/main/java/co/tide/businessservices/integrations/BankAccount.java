package co.tide.businessservices.integrations;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BankAccount {

    private UUID accountId;
    private Integer balance;
    private List<Integer> transactions;
    private AccountHolder accountHolder;
}
