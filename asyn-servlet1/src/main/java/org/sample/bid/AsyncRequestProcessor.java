package org.sample.bid;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

/**
 * Created by sagara on 10/22/16.
 */
public class AsyncRequestProcessor implements Runnable {

    private static final Logger logger = Logger.getLogger(AsyncRequestProcessor.class);

    private AsyncContext asyncContext;
    private boolean dispatch;

    public AsyncRequestProcessor(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
        dispatch = true;
    }

    public AsyncRequestProcessor(AsyncContext asyncContext,
                                 boolean dispatch) {
        this(asyncContext);
        this.dispatch = dispatch;
    }

    public void run() {
        //do something asynchronously –
        //  wait for a resource to become available
        //  or for a long running computation to finish
        String reqId = asyncContext.getRequest().getParameter("id");
        if (null == reqId || reqId.length() == 0) reqId = "<unk>";
        long id = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        if (null == threadName || threadName.length() == 0)
            threadName = "";
        String threadId = id + ":" + threadName;

        String result = longRunningProcess(reqId, threadId);

        //Store the result of the computation in a "view helper"
        ServletRequest req = asyncContext.getRequest();
        if (null != req && req.isAsyncStarted())
            req.setAttribute("result", result);
        else
            return;

        //once done, dispatch back to the final destination
        if (dispatch) {
            asyncContext.dispatch("/results.jsp");
        } else {
            //alternatively handle it ourselves
            String html = "Result of the process for request id: " +
                    reqId + "<br/> Started at:" +
                    asyncContext.getRequest().getAttribute("receivedAt") +
                    ".<br/> Completed at:" + result +
                    "<br/> Called complete.";
            asyncContext.getResponse().setContentType("text/html");
            try {
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println("");
            } catch (Exception e) {
                logger.log(Level.WARN, e.getMessage(), e);
            }
            asyncContext.complete();
        }
    }

    public String longRunningProcess(String reqId, String threadId) {
        Random randomGenerator = new Random();
        int randomInt = 5000 + randomGenerator.nextInt(10000);
        logger.info("AsyncRequestProcessor: Begin long run process (" +
                randomInt + " ms) for request: " +
                reqId + ". on thread: " + threadId +
                "[" + new Date() + "]");

        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException ie) {
            logger.log(Level.WARN, ie.getMessage(), ie);
        }

        logger.info("AsyncRequestProcessor: Done processing request: " +
                reqId + ". on thread: " +
                threadId + "[" + new Date() + "]");
        return new Date().toString();
    }
}
