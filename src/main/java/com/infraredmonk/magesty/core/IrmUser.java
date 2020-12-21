package com.infraredmonk.magesty.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
public class IrmUser {

    private String email;
    private String firstName;
    private String lastName;
    private Boolean registrationConfirmed;
    private DateTime registrationTime;
}
