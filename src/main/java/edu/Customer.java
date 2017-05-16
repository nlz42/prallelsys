package edu;

public class Customer implements Comparable<Customer> {
	
	private String name;
	private boolean hasGoldCard = false;
	
	public Customer(String name, Boolean hasGoldCard){
		this.name = name;
		this.hasGoldCard = hasGoldCard;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}	

	public boolean isHasGoldCard() {
		return hasGoldCard;
	}

	public void setHasGoldCard(boolean hasGoldCard) {
		this.hasGoldCard = hasGoldCard;
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

	@Override
	public int compareTo(Customer other) {
		if(this.hasGoldCard && !(other.hasGoldCard)){
			return -1;
		}
		if(this.hasGoldCard && other.hasGoldCard){
			return 0;
		}
		return 1;
	}
}
