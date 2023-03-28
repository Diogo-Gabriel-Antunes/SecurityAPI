package br.com.SecurityApi.Initializer;

import br.com.SecurityApi.Config.SecurityConfigurations;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfigurations.class);
    }
}