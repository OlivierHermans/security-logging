package be.olivierhermans.securitylogging.web.endpoint;

import be.olivierhermans.securitylogging.domain.model.exception.NoEntityFoundException;
import be.olivierhermans.securitylogging.domain.service.PersonService;
import be.olivierhermans.securitylogging.web.Action;
import be.olivierhermans.securitylogging.web.resource.PersonResource;
import be.olivierhermans.securitylogging.web.resource.mapper.PersonResourceMapper;
import be.olivierhermans.securitylogging.web.securitylogging.SecurityLoggable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPersonBySsnController {

    private final PersonService personService;
    private final PersonResourceMapper personResourceMapper;

    @GetMapping("/person/{ssn}")
    @SecurityLoggable(action = Action.GET_PERSON_BY_SSN)
    public PersonResource getPersonBySsn(@PathVariable("ssn") String ssn) {
        return personService.findPersonBySsn(ssn)
                .map(personResourceMapper::modelToDto)
                .orElseThrow(NoEntityFoundException::new);
    }
}
