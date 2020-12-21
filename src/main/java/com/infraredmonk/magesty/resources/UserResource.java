package com.infraredmonk.magesty.resources;

import com.infraredmonk.magesty.core.IrmUser;
import com.infraredmonk.magesty.jdbi3.IrmUserDao;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final IrmUserDao irmUserDao;

    @Inject
    public UserResource(IrmUserDao irmUserDao) {
        this.irmUserDao = irmUserDao;
    }

    @POST
    @Path("/register")
    public Response registerUser(@NotNull IrmUser irmUser) {
        try {
            irmUserDao.insertUser(irmUser);
            return Response.status(200).entity("User successfully registered.").build();
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.contains("violates unique constraint")) {
                log.debug("{}", message);
                return Response.status(409)
                        .entity("Email '" + irmUser.getEmail() + "' was previously registered!")
                        .build();
            } else {
                log.error("{}", message);
                return Response.status(500).entity(message).build();
            }
        }
    }

    @GET
    @Path("/{email}")
    public Response getUserByEmail(@NotNull @PathParam("email") String email) {
        List<IrmUser> usersByEmail = irmUserDao.findUserByEmail(email);
        int userCount = usersByEmail.size();
        if (userCount == 1) {
            return Response.status(200).entity(usersByEmail.get(0)).build();
        } else if (userCount == 0){
            return Response.status(404).entity("User with email '" + email + "' does not exist!").build();
        } else {
            throw new RuntimeException("Numbers of users by email '" + email + "' is " + userCount);
        }
    }

    @GET
    public Response getUsers(
            @DefaultValue("100") @QueryParam("limit") Integer limit,
            @DefaultValue("0") @QueryParam("offset") Integer offset) {
        List<IrmUser> allUsers = irmUserDao.listAllUsers(limit, offset);
        if (allUsers.size() > 0) {
            return Response.status(200).entity(allUsers).build();
        } else {
            return Response.status(404).entity("No users were found!").build();
        }
    }
}
