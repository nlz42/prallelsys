package hilzersBarberShop;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Shop {

	private static  LinkedBlockingQueue<Customer> couch = new LinkedBlockingQueue<Customer>(3);
	private static  LinkedBlockingQueue<Customer> waitingRoom = new LinkedBlockingQueue<Customer>(20);
	private static  LinkedBlockingQueue<Customer> wantToPay = new LinkedBlockingQueue<Customer>();

	public static void main(String[] args) {

		//create 3 barber
		for (int i=0;i<3;i++){
			Thread barber = new Thread(new Barber());
			barber.start();
		}

		//example Run with a few customer
		for (int i = 0; i < 3; i++) {
			Thread cust = new Thread(new Customer("Cust" + i));
			cust.start();
		}

	}

	// call from customer Threads
	public static void enterShop(Customer customer) {
		try {
			waitingRoom.put(customer);
			System.out.println(customer.getName() + " enter Shop");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Call from Customer Thread
	public static void enterCouch() {
		try {
			Customer customer = waitingRoom.take();
			couch.put(customer);
			System.out.println(customer.getName() + " sitting on couch");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Call from Barbers Threads
	public static Customer leaveCouch() {
		try {
			return couch.poll(20, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	//Call from Barber Threads
	public static void haveToPay(Customer customer) {
		try {
			wantToPay.put(customer);
			System.out.println(customer.getName() + " have to Pay");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//synchronized cause just one barber could be possible to access this!
	//Call from Barber
	public static synchronized Customer acceptPayment() {
		try {
			return wantToPay.poll(20, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
