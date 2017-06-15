package hilzersBarberShop;

public class Customer implements Runnable {
	
	private String name;
	
	public Customer (String name){
		this.name = name;
	}

	@Override
	public void run() {
		enterShop();
		sitOnSofa();
	}
	
	public void enterShop(){
		Shop.enterShop(this);
	}
	
	public void sitOnSofa(){
		Shop.enterCouch();
	}
	
	public void getHairCut(){
		try {
			System.out.println(name+" getHairCut");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void pay(){
		System.out.println(name+" pay");
	}
	
	public String getName(){
		return this.name;
	}
	
	public void leave(){
		System.out.println("Leave Shop");
	}

}
