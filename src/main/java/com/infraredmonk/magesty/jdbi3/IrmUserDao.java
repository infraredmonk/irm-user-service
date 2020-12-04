package com.infraredmonk.magesty.jdbi3;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IrmUserDao {

    @SqlUpdate("INSERT INTO irm_user(email, first_name, last_name) VALUES (:email, :first_name, :last_name)")
    void insertUser(@Bind("email") String email, @Bind("first_name") String firstName,
            @Bind("last_name") String lastName);
}
