package be.olivierhermans.securitylogging.domain.service.impl;

import be.olivierhermans.securitylogging.domain.model.Person;
import be.olivierhermans.securitylogging.domain.service.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private Map<String, Person> personMap = Map.of(
            "123", Person.builder()
                    .ssn("123")
                    .firstName("Al")
                    .lastName("Bundy")
                    .dateOfBirth(LocalDate.of(1958, 3, 17))
                    .build(),
            "456", Person.builder()
                    .ssn("345")
                    .firstName("Jefferson")
                    .lastName("Darcy")
                    .dateOfBirth(LocalDate.of(1965, 6, 1))
                    .build()
    );

    @Override
    public Optional<Person> findPersonBySsn(String ssn) {
        return Optional.ofNullable(personMap.get(ssn));
    }

    @Override
    public Collection<Person> getAllPeople() {
        return personMap.values();
    }
}
