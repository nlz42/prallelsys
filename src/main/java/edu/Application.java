package edu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	/**
	 * Oefffnungszeiten = 10-16 Uhr = 6 Stunden = 360 min 3 mal 120 min
	 * Vormittags: 10-12 nachmittags: 12-14 abenbens:14-16
	 */

	private static int countCustomer = 0;
	private static IceCreamShop shop;
	private static ExecutorService pool = Executors.newCachedThreadPool();
	//private static ExecutorService pool = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		// create IceShop
		shop = new IceCreamShop();

		for (int i = 0; i < 3; i++) {
			int j = i;
			pool.submit(() -> {
				new Customer("Customer" + j, shop).run();
			});
		}

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // after this the test is over
		System.out.println("Now the really Day ;-) \n \n \n \n");

		// Vormittags
		// startDayTime(countGroup,everymin,mingroupsize,maxgroupsize)
		System.out.println("Der Vormittag beginnt \n \n");
		startDayTime(10, 7, 1, 2);
		// Nachmittags
		System.out.println("\n \n Der Nachmittag beginnt \n \n");
		startDayTime(10, 3, 3, 5); // abends
		System.out.println("\n \n Der Abend beginnt \n \n");
		startDayTime(10, 10, 3, 6);

		System.out.println("\n ######## \n Nun müssen nur noch alle Gäste bedienet werde \n");
		
		pool.shutdown();

	}

	private static void startDayTime(int countGroups, int everyminute, int minGroupRange, int maxGroupRange) {
		int time = 0;
		while (time <= 120) {// vormittags
			if ((time % everyminute) == 0) {
				System.out.println("Eine neue Gruppe kommt zum Eisladen");
				for (int i = 0; i < 10; i++) {
					int group = randomGruopSize(minGroupRange, maxGroupRange);
					for (int j = 0; j < group; j++) {
						countCustomer++;
						int count = countCustomer;
						pool.submit(() -> {
							new Customer("Customer "+count, shop).run();
						});
					}
				}
			}
			time++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static int randomGruopSize(int min, int max) {
		int range = max - min + 1;
		int groupSize = (int) (Math.random() * range) + min;
		return groupSize;
	}

}
