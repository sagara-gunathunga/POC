package org.sample.client.basic;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by sagara on 10/26/16.
 */
public class HelloClientFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        final WebTarget target = ClientBuilder.newClient().target("http://localhost:8090/hello");
        Future<String> response = target.path("/{user}")
                .resolveTemplate("user", "sagara").request().async().get(String.class);

        //This won't call the remote server
        System.out.println("Response message : " + response);
        //This call the remote server
        System.out.println("Response message : " + response.get());


    }

}
