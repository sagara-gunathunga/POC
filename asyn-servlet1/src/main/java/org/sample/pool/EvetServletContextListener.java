package org.sample.pool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by sagara on 10/23/16.
 */
@WebListener
public class EvetServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        new Thread() {

            @Override
            public void run() {
                while (true) {
                    EventGenerator.fireScheduledEvent();
                    try {
                        Thread.sleep(1000*30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
