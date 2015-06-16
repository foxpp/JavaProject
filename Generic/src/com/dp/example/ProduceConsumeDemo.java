package com.dp.example;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
	Random random = new Random(55);
	void produce() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ProduceConsumeDemo.sharedqueue.size() < 50) {
				int product = random.nextInt();
				ProduceConsumeDemo.sharedqueue.add(product);
				System.out.println("producer: "+ product);
			}			
		}
	}

	@Override
	public void run() {
		produce();
	}

}

class Consumer implements Runnable {
	void consume() {
		while (true) {
			
			if (!ProduceConsumeDemo.sharedqueue.isEmpty()) {
				try {
					Thread.sleep(1000);
					System.out.println("consumer: " + Thread.currentThread().getName() + " - "+ ProduceConsumeDemo.sharedqueue.take().toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	@Override
	public void run() {
		consume();
	}
}

public class ProduceConsumeDemo {
	final static int SIZE = 50;
	public static BlockingQueue<Integer> sharedqueue = new ArrayBlockingQueue<Integer>(
			SIZE);
	public static void main(String[] args) {
		
		Thread producer =  new Thread(new Producer());
		producer.start();
		for (int i = 0; i < 10; i++) {
			Thread consumer = new Thread(new Consumer());
			consumer.start();
		}

	}
}
