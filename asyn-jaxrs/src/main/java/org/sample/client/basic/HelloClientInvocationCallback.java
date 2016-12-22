package org.sample.client.basic;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sagara on 10/26/16.
 */
public class HelloClientInvocationCallback {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        final WebTarget targetCorrect = ClientBuilder.newClient().target("http://localhost:8090/hello");
        final WebTarget targetWrong = ClientBuilder.newClient().target("http://localhost:8090/hellox");


        Future<Response> futureResponse = targetCorrect.path("/{user}").resolveTemplate("user", "sagara")
                .request().async().get(new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response response) {
                        System.out.println("Response status " + response.getStatus());
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.out.println("Response " + throwable);

                    }
                });

        System.out.println("Response status " + futureResponse.get().readEntity(String.class));




        Future<Response> errorResponse = targetWrong.path("/{user}").resolveTemplate("user", "sagara")
                .request().async().get(new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response response) {
                        System.out.println("Response status " + response.getStatus());
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.out.println("Response " + throwable);

                    }
                });

        System.out.println("Response status " + errorResponse.get().readEntity(String.class));

    }
}
