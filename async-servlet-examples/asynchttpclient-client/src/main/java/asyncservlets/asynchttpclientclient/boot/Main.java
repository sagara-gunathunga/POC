package asyncservlets.asynchttpclientclient.boot;

import java.util.concurrent.atomic.AtomicInteger;

import asyncservlets.asynchttpclientclient.infra.rest.client.SleepServerApiClient;
import asyncservlets.asynchttpclientclient.infra.rest.client.SleepServerApiClient.Callback;

public class Main {
	public static void main(String[] args) throws Exception {
//		if (args.length != 2) {
//			System.out
//					.println("Usage: <program> <requests> <replyAfterMillis>");
//			return;
//		}

//		int requests = Integer.parseInt(args[0]);
//		long replyAfterMillis = Long.parseLong(args[1]);

		int requests = 1;
		long replyAfterMillis =10000;

		final AtomicInteger responsesLeft = new AtomicInteger(requests);
		final SleepServerApiClient client = new SleepServerApiClient();
		final long before = System.currentTimeMillis();
		for (int i = 0; i < requests; i++)
			client.getSleepResource(replyAfterMillis, new Callback<String>() {
				@Override
				public void callback(String result) {
					if (responsesLeft.decrementAndGet() > 0)
						return;

					long time = System.currentTimeMillis() - before;
					System.out.println("Finished after " + time
							+ " milliseconds.");
					client.close();
				}
			});
	}
}
