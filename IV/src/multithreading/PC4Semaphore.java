package multithreading;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class SemQueue {
	Semaphore emptyCount = new Semaphore(5); //count of empty spots in queue
	Semaphore fullCount = new Semaphore(0); //count of items in queue
	Semaphore useQueue = new Semaphore(1);
	Queue<String> q = new ArrayDeque<String>();
	
	void put(String s) throws InterruptedException{
		emptyCount.acquire();
		useQueue.acquire();
		q.offer(s);
		useQueue.release();
		fullCount.release();
	}
	
	String get() throws InterruptedException{
		fullCount.acquire();
		useQueue.acquire();
		String item = q.poll();
		useQueue.release();
		emptyCount.release();
		return item;
	}
}

class Producer4 implements Runnable {
	SemQueue q;
	Producer4(SemQueue q) {
		this.q = q;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				q.put(i + "");
				System.out.println("Producing: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {}
	}
}

class Consumer4 implements Runnable {
	SemQueue q;
	Consumer4(SemQueue q) {
		this.q = q;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				System.out.println("Consuming: " + q.get());
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {}
	}
}

class PC4Semaphore {

	public static void main(String[] args) {
		SemQueue q = new SemQueue();
		new Thread(new Producer4(q)).start();
		new Thread(new Consumer4(q)).start();
	}

}
