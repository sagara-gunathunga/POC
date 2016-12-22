package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/hi")
@Log
public class Hi {


    @GET
    @Path("/{name}")
    public Response hi(@PathParam("name") String name) {
        String result = "Hi " + name;
        return Response.status(Response.Status.ACCEPTED).entity(result).build();
    }

}
