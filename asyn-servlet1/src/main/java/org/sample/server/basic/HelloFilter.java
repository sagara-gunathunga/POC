package org.sample.server.basic;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by sagara on 10/21/16.
 */

@WebFilter(urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "simpleParam", value = "paramValue")}, asyncSupported = true)
public class HelloFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        writer.println("===============");
        writer.println("Filter intercepted!");
        writer.println("===============");

        // Log the resulting string
        writer.flush();
        filterConfig.getServletContext().
                log(sw.getBuffer().toString());

        chain.doFilter(request, response);

    }

    public void destroy() {

    }
}
