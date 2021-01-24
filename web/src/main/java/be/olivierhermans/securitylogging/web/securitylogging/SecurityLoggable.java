package be.olivierhermans.securitylogging.web.securitylogging;

import be.olivierhermans.securitylogging.web.Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityLoggable {

    Action action();
}
