package org.sample.server.basic.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by sagara on 12/9/16.
 */
@Provider
public class LogHeaderRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("*************** Request Headers *********************");
        for(Map.Entry<String, List<String>> header : requestContext.getHeaders().entrySet()) {
            System.out.println("header => " + header);

        }
    }
}
