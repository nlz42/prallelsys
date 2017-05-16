package edu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Application {

	/**
	 * Oefffnungszeiten = 10-16 Uhr = 6 Stunden = 360 min 3 mal 120 min
	 * Vormittags: 10-12 nachmittags: 12-14 abenbens:14-16
	 */
	
	private static BlockingQueue<Customer> queue = new PriorityBlockingQueue<Customer>();

	public static void main(String[] args) {
		ProducerCustomer producer = new ProducerCustomer(queue);
		ConsumerEmployee consumer = new ConsumerEmployee(queue);
		// one producer for all Customer
		Thread producer1 = new Thread(producer);
		producer1.start();
		
		// three consumer analog for 3 employee
		Thread employee1 = new Thread(consumer);
		employee1.start();
		Thread employee2 = new Thread(consumer);
		employee2.start();
		Thread employee3 = new Thread(consumer);
		employee3.start();

		//check if no more customer comes
		try {
			producer1.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//Wait until queue is Empty
		while (!queue.isEmpty()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(7000); // wait until last customer is safely served!
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employee1.interrupt();
		employee2.interrupt();
		employee3.interrupt();
	}

}
