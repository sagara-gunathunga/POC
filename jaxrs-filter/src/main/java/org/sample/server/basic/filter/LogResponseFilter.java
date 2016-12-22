package org.sample.server.basic.filter;

import org.sample.server.basic.Log;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by sagara on 12/9/16.
 */
@Provider
@Log
public class LogResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("*************** Logging Response  *********************");
        System.out.println(responseContext);


    }
}
