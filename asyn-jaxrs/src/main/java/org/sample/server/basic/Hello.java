package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;


@Path("/hello")
public class Hello {


    @GET
    @Path("/{name}")
    public void helloResume(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        String result = "Hello " + name;
        asyncResponse.resume(result);
    }

    @GET
    @Path("cancel/{name}")
    public void helloCancel(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        asyncResponse.cancel(200);
    }


}
