package be.olivierhermans.securitylogging.web.mapper;

import be.olivierhermans.securitylogging.web.resource.PersonResource;
import be.olivierhermans.securitylogging.domain.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonResourceMapper {

    // if you want something done correctly, you unfortunately have to do it yourself...
    default PersonResource modelToDto(Person person) {
        return person == null
                ? null
                : PersonResource.builder()
                        .ssn(person.getSsn())
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .dateOfBirth(person.getDateOfBirth())
                        .build();
    }
}
