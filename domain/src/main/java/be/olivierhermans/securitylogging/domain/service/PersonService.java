package be.olivierhermans.securitylogging.domain.service;

import be.olivierhermans.securitylogging.domain.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonService {

    Optional<Person> findPersonBySsn(String ssn);
    Collection<Person> getAllPeople();
}
