package ProducerConsumer;

/** @author: Hongquan Yu
 *  @date: Feb 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
	/** The Producer and Consumer use notify() and wait() to ensure that each value placed
	 *  in the CubbyHole by the Producer is retrieved once and only once by the Consumer. */

public class CubbyHole {
	private int seq; 		// this is the condition variable.
	private boolean available = false;

	public synchronized int get() {		// synchronized block guarantees only one thread can get inside
		while (available == false) {
			try { wait(); } 
			catch (InterruptedException e) { }
		}
		available = false;
		notify();		// notifying waiting methods to start execution
		return seq;
	}

	public synchronized void put(int value) {	// release resource
		while (available == true) {		// busy wait.
			try { wait(); } 
			catch (InterruptedException e) { }
		}
		seq = value;
		available = true;
		notify();
	}
}
