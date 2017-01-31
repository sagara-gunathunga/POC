package org.sample.server.basic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by sagara on 10/21/16.
 */

@WebListener
public class HelloSessionListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context created!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed!");

    }
}
