package be.olivierhermans.securitylogging.web;

@FunctionalInterface
public interface IdProvider {

    Object provideId();
}
