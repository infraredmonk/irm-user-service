package com.infraredmonk.magesty.resources;

import com.infraredmonk.magesty.api.UserRegistrationRequest;

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

    @POST
    public Response registerUser(@NotNull UserRegistrationRequest request) {
        String email = request.getEmail();
        // check if user with this email already exists
        if (email.equals("user@infraredmonk.com")) {
            return Response.status(409)
                    .entity("User has already been registered with this email.")
                    .build();
        }
        // save this user to the database
        return Response.status(200)
                .entity("User successfully registered.")
                .build();
    }
}
