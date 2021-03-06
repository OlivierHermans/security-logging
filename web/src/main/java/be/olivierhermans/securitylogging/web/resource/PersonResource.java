package be.olivierhermans.securitylogging.web.resource;

import be.olivierhermans.securitylogging.web.IdProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonResource implements IdProvider {

    private final String ssn;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;

    @Override
    public Object provideId() {
        return ssn;
    }
}
