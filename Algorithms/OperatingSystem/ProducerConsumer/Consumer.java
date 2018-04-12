package ProducerConsumer;

/** @author: Hongquan Yu
 *  @date: Feb 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class Consumer extends Thread {
	private CubbyHole cubbyhole;
	private int number;

	public Consumer(CubbyHole c, int number) {
		cubbyhole = c;
		this.number = number;
	}

	public void run() {
		int value = 0;
		for (int i = 0; i < 10; i++) {
			value = cubbyhole.get();			// Consumer consumes resource!
			System.out.println("Consumer #" + this.number + " got: " + value);
		}
	}
}
