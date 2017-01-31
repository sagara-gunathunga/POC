package org.sample.pool;

import java.util.Date;

/**
 * Created by sagara on 10/23/16.
 */
public class EventGenerator {

    public static  void fireScheduledEvent() {
        SampleLongPollingNotifier.getNotifier().notifyPeers(new Date());
        System.out.println("fired");
    }

}
