/*
 * From
 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html
 * BlockingQueue is an implementation
 */
package multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PC3BlockingQueue {
	public static void main(String[] args) {
		BlockingQueue q = new LinkedBlockingQueue();
		Producer3 p = new Producer3(q);
		Consumer3 c1 = new Consumer3(q);
		Consumer3 c2 = new Consumer3(q);
		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}
}

class Producer3 implements Runnable {
	private final BlockingQueue queue;

	Producer3(BlockingQueue q) {
		queue = q;
	}

	public void run() {
	     try {
	       while (true) { queue.put(produce()); }
	     } catch (InterruptedException ex) {}
	   }

	Object produce() {
		return new Object();
	}
}

class Consumer3 implements Runnable {
	private final BlockingQueue queue;

	Consumer3(BlockingQueue q) {
		queue = q;
	}

	public void run() {
	     try {
	       while (true) { consume(queue.take()); }
	     } catch (InterruptedException ex) {}
	   }

	void consume(Object x) {}
}
