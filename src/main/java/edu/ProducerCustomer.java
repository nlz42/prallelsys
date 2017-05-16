package edu;

import java.util.concurrent.BlockingQueue;

public class ProducerCustomer implements Runnable {

	private BlockingQueue<Customer> queue = null;
	private int id = 0;

	public ProducerCustomer(BlockingQueue<Customer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {

		System.out.println("Der Vormittag beginnt \n \n");
		startDayTime(10, 7, 1, 2);
		// Nachmittags
		System.out.println("\n \n Der Nachmittag beginnt \n \n");
		startDayTime(10, 3, 3, 5); // abends
		System.out.println("\n \n Der Abend beginnt \n \n");
		startDayTime(10, 10, 3, 6);
		System.out.println("\n ######## \n Nun müssen nur noch alle Gäste bedienet werde \n");

	}

	private void startDayTime(int countGroups, int everyminute, int minGroupRange, int maxGroupRange) {
		int time = 0;
		while (time <= 120) { // every 120 min change from
								// vormittag->mittag-abend
			if ((time % everyminute) == 0) {
				System.out.println("Eine neue Gruppe kommt zum Eisladen");
				for (int i = 0; i < 10; i++) {
					int group = randomNumber(minGroupRange, maxGroupRange);
					for (int j = 0; j < group; j++) {
						id++;
						int goldCard = randomNumber(1, 10);
						try {
							if (goldCard <= 4) {

								queue.put(new Customer("Customer P" + id, true));
							} else {
								queue.put(new Customer("Customer " + id, false));
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			time++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private int randomNumber(int min, int max) {
		int range = max - min + 1;
		int groupSize = (int) (Math.random() * range) + min;
		return groupSize;
	}

}
