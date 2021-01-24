package be.olivierhermans.securitylogging.web.resource;

import be.olivierhermans.securitylogging.web.IdProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonListResource implements IdProvider {

    private final List<PersonResource> people;

    @Override
    public Object provideId() {
        return people.stream().map(PersonResource::provideId).collect(toList());
    }
}
