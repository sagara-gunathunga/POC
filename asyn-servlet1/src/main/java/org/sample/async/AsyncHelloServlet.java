package org.sample.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sagara on 10/21/16.
 */

@WebServlet(urlPatterns = {"/async/hello"}, asyncSupported = true)
public class AsyncHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        final long startTime = System.nanoTime();
        final AsyncContext asyncContext = request.startAsync(request, response);
        PrintThreadInfo(Thread.currentThread());


        new Thread() {

            @Override
            public void run() {
                try {
                    PrintThreadInfo(Thread.currentThread());
                    ServletResponse response = asyncContext.getResponse();
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    Thread.sleep(2000);
                    out.print("Work completed. Time elapsed: " + (System.nanoTime() - startTime));
                    out.flush();
                    asyncContext.complete();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

    }

    private void PrintThreadInfo(Thread thread) {
        System.out.println("Current thread name = " + thread.getName() +
                "  , id = "+ thread.getId() +
                "  , Group  = " + thread.getThreadGroup());


    }
}
