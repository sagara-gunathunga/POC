package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;


@Path("/callback")
public class HelloCompletionCallback {


    @GET
    @Path("/{name}")
    public void helloResume(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        String result = "Hello " + name;
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable != null) {
                    throwable.printStackTrace();
                } else {
                    if (asyncResponse.isCancelled()) {
                        System.out.println("Request processing is CANCELED");
                    } else if (asyncResponse.isDone()) {
                        System.out.println("Request processing is DONE");
                    }

                }


            }
        });
        asyncResponse.resume(result);
    }


    @GET
    @Path("cancel/{name}")
    public void helloCancel(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        String result = "Hello " + name;
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable != null) {
                    throwable.printStackTrace();
                } else {
                    if (asyncResponse.isCancelled()) {
                        System.out.println("Request processing is CANCELED");
                    } else if (asyncResponse.isDone()) {
                        System.out.println("Request processing is DONE");
                    }

                }


            }
        });
        asyncResponse.cancel();
    }


}
