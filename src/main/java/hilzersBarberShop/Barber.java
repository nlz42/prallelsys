package hilzersBarberShop;

public class Barber implements Runnable {


	public Barber() {
	}

	@Override
	public void run() {
		while (true) {
			Customer cust = Shop.leaveCouch();
			if (cust != null) {
				System.out.println(cust.getName() + " leave Couch");
				cutHair(cust);
			}
			cust = Shop.acceptPayment();
			if (cust != null) {
				acceptPayment(cust);
			}
		}
	}

	public void cutHair(Customer customer) {
		customer.getHairCut();
		Shop.haveToPay(customer);
	}

	public void acceptPayment(Customer customer) {
		customer.pay();
		System.out.println(customer.getName() + " AcceptPayment");
		customer.leave();
	}
}
