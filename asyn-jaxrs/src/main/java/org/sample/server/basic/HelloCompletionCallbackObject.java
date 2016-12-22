package org.sample.server.basic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;


@Path("/callbackobj")
public class HelloCompletionCallbackObject {


    @GET
    @Path("/{name}")
    public void helloResume(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        String result = "Hello " + name;
        asyncResponse.register(new HelloCompletionCallback(asyncResponse));
        asyncResponse.resume(result);
    }


    @GET
    @Path("cancel/{name}")
    public void helloCancel(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
        asyncResponse.register(new HelloCompletionCallback(asyncResponse));
        asyncResponse.cancel();
    }


    class HelloCompletionCallback implements CompletionCallback {

        AsyncResponse asyncResponse;

        public HelloCompletionCallback(AsyncResponse asyncResponse) {
            this.asyncResponse = asyncResponse;
        }

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
    }


}
