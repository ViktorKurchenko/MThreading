package net.jcluster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Thread thread = new Thread() {

            @Override
            public void run() {
                System.out.println("Start thread: " + Thread.currentThread().getName());
                while(!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("iteration");
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                        break;
                    }
                }
                System.out.println("Finish thread: " + Thread.currentThread().getName());
            }

            @Override
            public void interrupt() {
                super.interrupt();
                System.out.println("interrupted-2");
            }

        };
        executor.submit(thread);
        thread.interrupt();
        executor.shutdown();
    }
}
