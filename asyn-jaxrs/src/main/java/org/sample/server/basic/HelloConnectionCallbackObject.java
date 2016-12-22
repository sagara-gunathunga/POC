package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;

//TODO - NOT WORKING
@Path("/callbackcon")
public class HelloConnectionCallbackObject {


    @GET
    @Path("/{name}")
    public void helloResume(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {

        String result = "Hello " + name;
        asyncResponse.register(new HelloConnectionCallback());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processing is done ");
        asyncResponse.resume(result);
    }


    class HelloConnectionCallback implements ConnectionCallback {

        @Override
        public void onDisconnect(AsyncResponse disconnected) {
            System.out.println("Connection disconnected :: " + disconnected);

        }
    }


}
