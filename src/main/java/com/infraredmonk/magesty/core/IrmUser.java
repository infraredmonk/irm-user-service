package com.infraredmonk.magesty.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IrmUser {

    private String email;
    private String firstName;
    private String lastName;
    private Boolean registrationConfirmed;
    private DateTime registrationTime;
}
