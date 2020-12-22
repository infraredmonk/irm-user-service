package com.infraredmonk.magesty.auth;

import com.infraredmonk.magesty.config.IrmAuthConfig;
import com.infraredmonk.magesty.config.IrmAuthUser;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;
import java.util.Set;

public class ServiceAuthenticator implements Authenticator<BasicCredentials, ServiceTokenPrincipal> {

    private static final Set<String> validRoles = Set.of(ServiceRoles.GUEST, ServiceRoles.CLIENT, ServiceRoles.ADMIN);

    private final IrmAuthConfig authConfig;

    public ServiceAuthenticator(IrmAuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    @Override
    public Optional<ServiceTokenPrincipal> authenticate(BasicCredentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        Optional<IrmAuthUser> authUser = authConfig.findUserByCredentials(username, password);
        if (authUser.isPresent()) {
            String name = authUser.get().getName();
            String role = authUser.get().getRole();
            if (validRoles.contains(role)) {
                return Optional.of(new ServiceTokenPrincipal(name, Set.of(new String[] {role})));
            } else {
                throw new IllegalStateException("Unexpected role: " + role);
            }
        }
        return Optional.empty();
    }
}
