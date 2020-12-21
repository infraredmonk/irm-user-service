package com.infraredmonk.magesty.jdbi3;

import com.infraredmonk.magesty.core.IrmUser;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface IrmUserDao {

    @SqlUpdate("INSERT INTO irm_user(email, first_name, last_name) VALUES (:email, :firstName, :lastName)")
    void insertUser(@BindBean IrmUser irmUser);

    @SqlQuery("SELECT * FROM irm_user WHERE email = :email")
    @RegisterBeanMapper(IrmUser.class)
    List<IrmUser> findUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM irm_user ORDER BY last_name LIMIT :limit OFFSET :offset")
    @RegisterBeanMapper(IrmUser.class)
    List<IrmUser> listAllUsers(@Bind("limit") int limit, @Bind("offset") int offset);
}
