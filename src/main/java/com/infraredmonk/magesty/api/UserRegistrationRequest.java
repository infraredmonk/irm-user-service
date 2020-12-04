package com.infraredmonk.magesty.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequest {

    private String email;
    private String firstName;
    private String lastName;
}
