package be.olivierhermans.securitylogging.web;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packages = "be.olivierhermans.securitylogging.web.resource")
public class ResourcesShouldImplementIdProviderTest {

    @ArchTest
    public static final ArchRule rule = ArchRuleDefinition.classes()
            .that().areNotMemberClasses()
            .should().implement(IdProvider.class);
}
