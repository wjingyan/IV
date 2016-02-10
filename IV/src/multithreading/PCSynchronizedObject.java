package multithreading;
/**
 * Adapted from
 * http://java67.blogspot.com/2012/12/producer-consumer-problem-with-wait-and-notify-example.html
 * This solution uses synchronized object.
 * More granular lock. Turns out we only need lock for add/remove and notifyAll();
 */

import java.util.Vector;

public class PCSynchronizedObject {

     public static void main(String args[]) {
         Vector sharedQueue = new Vector();
         int size = 4;
         Thread prodThread = new Thread(new Producer2(sharedQueue, size), "Producer");
         Thread consThread = new Thread(new Consumer2(sharedQueue, size), "Consumer");
         prodThread.start();
         consThread.start();
     }
}

class Producer2 implements Runnable {

     private final Vector sharedQueue;
     private final int SIZE;

     public Producer2(Vector sharedQueue, int size) {
         this.sharedQueue = sharedQueue;
         this.SIZE = size;
     }

     @Override
     public void run() {
         for (int i = 0; i < 7; i++) {
             System.out.println("Produced: " + i);
             try {
                 produce(i);
             } catch (InterruptedException ex) {}
         }
     }

     private void produce(int i) throws InterruptedException {

         //wait if queue is full
         while (sharedQueue.size() == SIZE) {
             synchronized (sharedQueue) {
                 System.out.println("Queue is full " + Thread.currentThread().getName() 
                                    + " is waiting , size: " + sharedQueue.size());
                 sharedQueue.wait();
             }
         }

         //producing element and notify consumers
         synchronized (sharedQueue) {
             sharedQueue.add(i);
             sharedQueue.notifyAll();
         }
     }
}

class Consumer2 implements Runnable {

     private final Vector sharedQueue;
     private final int SIZE;

     public Consumer2(Vector sharedQueue, int size) {
         this.sharedQueue = sharedQueue;
         this.SIZE = size;
     }

     @Override
     public void run() {
         while (true) {
             try {
                 System.out.println("Consumed: " + consume());
                 Thread.sleep(50);
             } catch (InterruptedException ex) {}
         }
     }

     private int consume() throws InterruptedException {
         //wait if queue is empty
         while (sharedQueue.isEmpty()) {
             synchronized (sharedQueue) {
                 System.out.println("Queue is empty " + Thread.currentThread().getName()
                                    + " is waiting , size: " + sharedQueue.size());
                 sharedQueue.wait();
             }
         }

         //Otherwise consume element and notify waiting producer
         synchronized (sharedQueue) {
             sharedQueue.notifyAll();
             return (Integer) sharedQueue.remove(0);
         }
     }
}


