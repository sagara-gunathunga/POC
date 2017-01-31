package org.sample.bid;

import org.apache.log4j.Logger;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

/**
 * Created by sagara on 10/22/16.
 */
public class BidAsyncListener implements AsyncListener {

    private static final Logger logger =
            Logger.getLogger(BidAsyncListener.class);

    public void onComplete(AsyncEvent ae) {
        logger.info("AsyncListener: onComplete for request: " +
                ae.getAsyncContext().getRequest().getParameter("id"));
    }

    public void onTimeout(AsyncEvent ae) {
        logger.info("AsyncListener: onTimeout for request: " +
                ae.getAsyncContext().getRequest().getParameter("id"));
    }

    public void onError(AsyncEvent ae) {
        logger.info("AsyncListener: onError for request: " +
                ae.getAsyncContext().getRequest().getParameter("id"));
    }

    public void onStartAsync(AsyncEvent ae) {
        logger.info("AsyncListener: onStartAsync");
    }
}
