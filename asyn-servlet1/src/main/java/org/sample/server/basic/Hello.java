package org.sample.server.basic;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

/**
 * Created by sagara on 10/21/16.
 *
 * TODO - Not Compelte
 */
public class Hello implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        writer.println("=============== onStartup()");
        writer.close();
    }
}
