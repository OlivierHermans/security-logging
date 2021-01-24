package be.olivierhermans.securitylogging.web;

import be.olivierhermans.securitylogging.web.securitylogging.SecurityLoggable;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packages = "be.olivierhermans.securitylogging.web.endpoint")
public class PublicEndpointsShouldBeAnnotatedWithSecurityLoggableTest {

    @ArchTest
    public static final ArchRule rule = ArchRuleDefinition.methods()
            .that().arePublic()
            .should().beAnnotatedWith(SecurityLoggable.class);
}
