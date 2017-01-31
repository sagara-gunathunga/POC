package org.sample.pool;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sagara on 10/23/16.
 */
public class SampleLongPollingNotifier {

    private static final SampleLongPollingNotifier notifier = new SampleLongPollingNotifier();

    private final Queue<AsyncContext> peers = new ConcurrentLinkedQueue();

    public static SampleLongPollingNotifier getNotifier(){
        return notifier;
    }

    public void notifyPeers(Date date) {
        for (AsyncContext ac : peers) {
            try {
                final ServletOutputStream os = ac.getResponse().getOutputStream();
                os.println(date.toString());
                ac.complete();
            } catch (IOException ex) {
                // Nothing ToDo: Connection was most likely closed by client.
            } finally {
                peers.remove(ac);
            }
        }
    }

    /**
     * Add async-request for event notification.
     *
     * @param ac AsyncContext
     */
    public void addAsyncContext(final AsyncContext ac) {
        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                peers.remove(ac);
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                peers.remove(ac);
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                peers.remove(ac);
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
            }
        });
        peers.add(ac);
    }
}
