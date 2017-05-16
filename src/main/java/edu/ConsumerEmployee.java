package edu;

import java.util.concurrent.BlockingQueue;

public class ConsumerEmployee implements Runnable {
	
	private BlockingQueue<Customer> queue =  null;

	public ConsumerEmployee(BlockingQueue<Customer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()){
			try {
				Customer customer = queue.take();
				System.out.println(customer.getName());
				customer.sleep();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
			
		}
	}

}
