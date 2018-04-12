package ProducerConsumer;

/** @author: Hongquan Yu
 *  @date: Feb 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class ProducerConsumerTest {
	public static void main(String args[]) {
		CubbyHole c = new CubbyHole();
		Producer p1 = new Producer(c, 1);
		Consumer c1 = new Consumer(c, 1);

		p1.start();
		c1.start();
	}
}
