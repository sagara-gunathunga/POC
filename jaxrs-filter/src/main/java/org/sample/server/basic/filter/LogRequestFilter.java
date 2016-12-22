package org.sample.server.basic.filter;

import org.sample.server.basic.Log;

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
@Log
public class LogRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("*************** Logging Request  *********************");
        System.out.println(requestContext);
    }
}
