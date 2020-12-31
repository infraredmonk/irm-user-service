package com.infraredmonk.magesty.config;

import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;
import java.util.Set;

@Data
public class IrmAuthConfig {

    private Set<IrmAuthUser> authUsers;

    public Optional<IrmAuthUser> findUserByCredentials(String username, String password) {
        return authUsers.stream()
                .filter(u -> u.getName().equals(username) && BCrypt.checkpw(password, u.getPassword()))
                .findFirst();
    }
}
