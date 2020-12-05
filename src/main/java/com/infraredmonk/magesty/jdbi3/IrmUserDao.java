package com.infraredmonk.magesty.jdbi3;

import com.infraredmonk.magesty.core.IrmUser;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface IrmUserDao {

    @SqlUpdate("INSERT INTO irm_user(email, first_name, last_name) VALUES (:email, :first_name, :last_name)")
    void insertUser(@Bind("email") String email,
            @Bind("first_name") String firstName,
            @Bind("last_name") String lastName);

    @SqlQuery("SELECT * FROM irm_user WHERE email = :email")
    @RegisterBeanMapper(IrmUser.class)
    List<IrmUser> findUserByEmail(@Bind("email") String email);
}
