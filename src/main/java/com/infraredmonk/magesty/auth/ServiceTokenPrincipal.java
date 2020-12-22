package com.infraredmonk.magesty.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public final class ServiceTokenPrincipal implements Principal {

    private final String name;
    private final Set<String> roles;
}
