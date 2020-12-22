package com.infraredmonk.magesty.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingResource {

    @GET
    @PermitAll
    public Response ping() {
        return Response.status(200)
                .entity("pong")
                .build();
    }
}
