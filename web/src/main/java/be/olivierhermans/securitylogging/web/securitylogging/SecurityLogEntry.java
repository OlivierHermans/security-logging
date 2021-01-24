package be.olivierhermans.securitylogging.web.securitylogging;

import be.olivierhermans.securitylogging.web.Action;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SecurityLogEntry {

    private final Action action;
    private final String path;
    private final Object result;
}
