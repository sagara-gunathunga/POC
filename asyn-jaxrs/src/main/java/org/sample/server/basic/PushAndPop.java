package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Path("/hello")
public class PushAndPop {

    private static final ConcurrentMap<String, AsyncResponse> suspended = new ConcurrentHashMap<>(10);

    @GET
    @Path("push/{name}")
    public void push(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        suspended.putIfAbsent(name, asyncResponse);

    }

    @GET
    @Path("pop/{name}")
    public String pop(@PathParam("name") String name) {
        AsyncResponse ar = suspended.get(name);
        if (ar != null) {
            suspended.remove(name);
            String result = "Hello " + name;
            ar.resume(result);
        }
        return "Message sent";
    }


}
