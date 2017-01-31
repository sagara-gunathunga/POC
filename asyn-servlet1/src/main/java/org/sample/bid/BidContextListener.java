package org.sample.bid;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by sagara on 10/22/16.
 */
@WebListener
public class BidContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(BidContextListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context Listener > Initialized");

        //Creation of a global async Executor
        Executor executor =
                new ThreadPoolExecutor(10, 10, 50000L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(100));
        sce.getServletContext().setAttribute("executor", executor);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
