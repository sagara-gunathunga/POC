package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;


@Path("/helloto")
public class HelloTimeoutHandler {


    @GET
    @Path("/to/{userName}")
    @Produces({"application/json"})
    public void getResourceTo(
            @PathParam("userName") final String userName,
            @Context HttpHeaders headers,
            @Suspended AsyncResponse async) {

        //release the request thread to accept more incoming requests
        async.setTimeout(50, TimeUnit.MILLISECONDS);

        async.setTimeoutHandler(new TimeoutHandler() {

            public void handleTimeout(AsyncResponse asyncResponse) {
                asyncResponse.resume(Response.status
                        (Response.Status.SERVICE_UNAVAILABLE)
                        .entity("TIME OUT !").build());
            }

        });

        try {
            Thread.sleep(600 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        async.resume("Hello Timeout " + userName);
    }

    @GET
    @Path("/notto/{userName}")
    @Produces({"application/json"})
    public void getResource(
            @PathParam("userName") final String userName,
            @Context HttpHeaders headers,
            @Suspended AsyncResponse async) {

        //release the request thread to accept more incoming requests
        async.setTimeout(50, TimeUnit.MILLISECONDS);

        async.setTimeoutHandler(new TimeoutHandler() {

            public void handleTimeout(AsyncResponse asyncResponse) {
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation time out.").build());
            }

        });

        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        async.resume("Hello Before Timeout " + userName);
    }

}
