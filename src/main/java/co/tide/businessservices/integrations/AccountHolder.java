package co.tide.businessservices.integrations;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccountHolder {

    private String name;
    private CountryCode country;
}
