package be.olivierhermans.securitylogging.web;

import be.olivierhermans.securitylogging.web.securitylogging.SecurityLoggable;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class SecurityLoggingForPublicEndpointsTest {

    @Test
    void public_endpoints_should_be_annotated_with_securityloggable() {
        ArchRule rule = ArchRuleDefinition.methods()
                .that().arePublic()
                .should().beAnnotatedWith(SecurityLoggable.class);

        rule.check(new ClassFileImporter().importPackages("be.olivierhermans.securitylogging.web.endpoint"));
    }
}
