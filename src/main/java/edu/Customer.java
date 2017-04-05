package edu;

public class Customer  implements Runnable {
	
	private String name;
	private IceCreamShop shop;
	
	public Customer(String name, IceCreamShop shop){
		this.name = name;
		this.shop = shop;
	}

	public void run() {
		shop.enter(this);
		shop.servedCustomer(this);
		shop.leave(this);
	}

	public String getName() {
		return name;
	}

	public void sleep() {
		int max = 6;
		int min = 3;
		int range = max - min +1;
		int servedTime = (int)(Math.random()*range)+min;
		System.out.println(this.name+" sein Eis braucht "+servedTime+" minuten");
		try {
			Thread.sleep(servedTime*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	

}
