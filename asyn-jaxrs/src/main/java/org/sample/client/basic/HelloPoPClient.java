package org.sample.client.basic;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sagara on 10/26/16.
 */
//TODO - This is not what I expect as 'async' client
public class HelloPoPClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        Client client = ClientBuilder.newClient().
                property("http.connection.timeout", 100).property("http.receive.timeout", 10);
        final WebTarget target = client.target("http://localhost:8090/hello/push/sagara");


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Response> response = target.request().async().get(new InvocationCallback<Response>() {
            @Override
            public void completed(Response response) {
                System.out.println("Response message  : " + response.readEntity(String.class));

            }

            @Override
            public void failed(Throwable throwable) {
                System.out.println("Response " + throwable);

            }
        });


        executorService.execute(() -> {
            try {
                response.get();
                executorService.shutdown();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("I'm not waiting ........ ");


    }

}
