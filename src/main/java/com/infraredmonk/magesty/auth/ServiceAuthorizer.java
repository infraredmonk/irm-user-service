package com.infraredmonk.magesty.auth;

import io.dropwizard.auth.Authorizer;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class ServiceAuthorizer implements Authorizer<ServiceTokenPrincipal> {

    @Override
    public boolean authorize(ServiceTokenPrincipal principal, String role) {
        return principal.getRoles().contains(role);
    }

    @Override
    public boolean authorize(ServiceTokenPrincipal principal, String role,
            @Nullable ContainerRequestContext requestContext) {
        return principal.getRoles().contains(role);
    }
}
