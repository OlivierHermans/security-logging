package be.olivierhermans.securitylogging.web.endpoint;

import be.olivierhermans.securitylogging.domain.service.PersonService;
import be.olivierhermans.securitylogging.web.Action;
import be.olivierhermans.securitylogging.web.resource.PersonListResource;
import be.olivierhermans.securitylogging.web.mapper.PersonResourceMapper;
import be.olivierhermans.securitylogging.web.securitylogging.SecurityLoggable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class GetAllPeopleController {

    private final PersonService personService;
    private final PersonResourceMapper personResourceMapper;

    @GetMapping("/person")
    @SecurityLoggable(action = Action.GET_ALL_PEOPLE)
    public PersonListResource getAllPeople() {
        return PersonListResource.builder()
                .people(personService.getAllPeople().stream().map(personResourceMapper::modelToDto).collect(toList()))
                .build();
    }
}
