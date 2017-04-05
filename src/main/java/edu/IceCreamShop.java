package edu;


public class IceCreamShop {
	
	private int freeEmployee = 3; // can served max three customer
	
	public IceCreamShop(int emplployes){
		this.freeEmployee = emplployes;
	}
	
	public synchronized void enter(Customer customer) {
		System.out.println(customer.getName()+" betritt laden");
		while (freeEmployee == 0){
				try {
					System.out.println(customer.getName()+" muss warten");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		freeEmployee--;
	}
	
	public synchronized void leave(Customer customer){
		System.out.println(customer.getName()+" geht glücklich");
		freeEmployee++;
		notify();
	}
	
	public void servedCustomer(Customer customer){
		System.out.println(customer.getName()+" wird bedient");
		customer.sleep();
		System.out.println(customer.getName()+" wurde bedient");
	}

}
