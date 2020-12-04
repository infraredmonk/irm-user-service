package com.infraredmonk.magesty.resources;

import com.infraredmonk.magesty.api.UserRegistrationRequest;
import com.infraredmonk.magesty.jdbi3.IrmUserDao;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/users/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRegistrationResource {

    IrmUserDao irmUserDao;

    public UserRegistrationResource(IrmUserDao irmUserDao) {
        this.irmUserDao = irmUserDao;
    }

    @POST
    public Response registerUser(@NotNull UserRegistrationRequest request) {
        irmUserDao.insertUser(request.getEmail(), request.getFirstName(), request.getLastName());
        // save this user to the database
        return Response.status(200)
                .entity("User successfully registered.")
                .build();
    }
}
