package org.sample.pool;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sagara on 10/23/16.
 */
@WebServlet(urlPatterns = {"/polling"}, asyncSupported = true, loadOnStartup = 1)
public class SampleLongPollingRegistration extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setStatus(202);
        response.setHeader("Pragma", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.flushBuffer();

        final AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(300000);

        SampleLongPollingNotifier.getNotifier().addAsyncContext(asyncContext);
    }
}
