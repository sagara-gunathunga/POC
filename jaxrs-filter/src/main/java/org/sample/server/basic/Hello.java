package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;


@Path("/hello")
public class Hello {


    @GET
    @Path("/{name}")
    public Response hello(@PathParam("name") String name) {
        String result = "Hello " + name;
        return Response.status(Response.Status.ACCEPTED).entity(result).build();
    }

    @GET
    @Path("/log/{name}")
    @Log
    public Response helloWithLog(@PathParam("name") String name) {
        String result = "Hello " + name;
        return Response.status(Response.Status.ACCEPTED).entity(result).build();
    }

}
