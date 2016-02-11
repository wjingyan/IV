package multithreading;
/*
 * Oracle documentation example
 */

class Drop {
	private String message;
	private boolean empty = true;
	
	public synchronized String take() {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		empty = true;
		notifyAll(); //notify producer
		return message;
	}
	
	public synchronized void put(String message) {
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		empty = false;
		this.message = message;
		notifyAll(); // notify consumer
	}
}

class Producer1 implements Runnable {
	private Drop drop;
	
	public Producer1(Drop drop) {
		this.drop = drop;
	}

	@Override
	public void run() {
		String info[] = {"msg1", "msg2", "msg3", "msg4"};
		for (int i = 0; i < info.length; i++) {
			drop.put(info[i]);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
		}
		drop.put("DONE");
	}
}

class Consumer1 implements Runnable {
	private Drop drop;
	
	public Consumer1(Drop drop) {
		this.drop = drop;
	}

	@Override
	public void run() {
		for (String message = drop.take();
				!message.equals("DONE");
				message = drop.take()) {
			System.out.format("MESSAGE RECEIVED: %s%n", message);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
		}
		
	}
}

public class PC1GuardedBlocks {
	public static void main(String[] args) {
		Drop drop = new Drop();
		(new Thread(new Producer1(drop))).start();
		(new Thread(new Consumer1(drop))).start();
	}
}
