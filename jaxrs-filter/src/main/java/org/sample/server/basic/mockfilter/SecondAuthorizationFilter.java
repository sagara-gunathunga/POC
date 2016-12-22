package org.sample.server.basic.mockfilter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by sagara on 12/9/16.
 */
@Provider
@PreMatching
@Priority(4)
public class SecondAuthorizationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("++++++++++++++++++++ Authenticate with Second Filter ++++++++++++++++++");

    }
}
