package com.infraredmonk.magesty.resources;

import com.infraredmonk.magesty.api.UserRegistrationRequest;
import com.infraredmonk.magesty.core.IrmUser;
import com.infraredmonk.magesty.jdbi3.IrmUserDao;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
public class UserRegistrationResource {

    IrmUserDao irmUserDao;

    public UserRegistrationResource(IrmUserDao irmUserDao) {
        this.irmUserDao = irmUserDao;
    }

    @POST
    @Path("/register")
    public Response registerUser(@NotNull UserRegistrationRequest request) {
        try {
            irmUserDao.insertUser(request.getEmail(), request.getFirstName(), request.getLastName());
            return Response.status(200).entity("User successfully registered.").build();
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.contains("violates unique constraint")) {
                log.debug("{}", message);
                return Response.status(409)
                        .entity("Email '" + request.getEmail() + "' was previously registered!")
                        .build();
            } else {
                log.error("{}", message);
                return Response.status(500).entity(message).build();
            }
        }
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@NotNull @PathParam("email") String email) {
        List<IrmUser> usersByEmail = irmUserDao.findUserByEmail(email);
        if (usersByEmail.size() == 1) {
            return Response.status(200).entity(usersByEmail).build();
        } else {
            return Response.status(404).entity("User with email '" + email + "' does not exist!").build();
        }
    }
}
