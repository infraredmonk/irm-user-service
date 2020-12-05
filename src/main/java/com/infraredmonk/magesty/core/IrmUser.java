package com.infraredmonk.magesty.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IrmUser {

    private String email;
    @ColumnName("first_name")
    private String firstName;
    @ColumnName("last_name")
    private String lastName;
    @ColumnName("registration_confirmed")
    private Boolean registrationConfirmed;
}
