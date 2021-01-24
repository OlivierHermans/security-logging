package be.olivierhermans.securitylogging.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Person {

    private final String ssn;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
}
