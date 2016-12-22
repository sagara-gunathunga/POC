package org.sample.client.basic;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sagara on 10/26/16.
 */
public class HelloClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        Client client = ClientBuilder.newClient();
        final AsyncInvoker asyncInvoker = client.target("http://localhost:8090/hello").path("/{user}")
                .resolveTemplate("user", "sagara").request().async();
        final Future<Response> responseFuture = asyncInvoker.get();

        System.out.println("Request is being processed asynchronously.");
        final Response response = responseFuture.get();
        System.out.println("Response message : " + response.readEntity(String.class));

    }
}
