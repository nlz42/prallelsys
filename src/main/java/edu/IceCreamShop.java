package edu;

import java.util.concurrent.Semaphore;

public class IceCreamShop {

	private Semaphore emplployes = new Semaphore(3);

	public IceCreamShop() {
	}

	public void enter(Customer customer) {
		try {
			System.out.println(customer.getName() + " betritt laden");
			emplployes.acquire();
			System.out.println(customer.getName() + " wird bedient");

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} 
	}

	public void leave(Customer customer) {
		try {
			System.out.println(customer.getName() + " geht glücklich");
		} finally {
			emplployes.release();
		}
	}

	public void servedCustomer(Customer customer) {
		customer.sleep();
		System.out.println(customer.getName() + " wurde bedient");
	}

}
