package DiningPhilosopher;

import java.util.Random;

/**
 * @author: Hongquan Yu
 * @date: Feb 17, 2018
 *
 * @From: University of Maryland, College Park
 * @Email: hyu12346@terpmail.umd.edu
 */
public class Philosopher implements Runnable {
	private int id;
	private Chopstick leftChopstick;			// A philosopher has two Chopsticks!
	private Chopstick rightChopstick;
	private Random random;
	private int eatingCounter;
	private volatile boolean isFull = false; 	// 

	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
		this.random = new Random();
	}

	@Override
	public void run() {

		try {
			while (!isFull) {		// 当哲学家们还没吃饱的时候
				
				think();
				
				if (leftChopstick.pickUp(this, State.LEFT)) {		// 左边筷子已经拿起来
					if (rightChopstick.pickUp(this, State.RIGHT)) {	// 左边筷子已经拿起来
						eat();
						rightChopstick.putDown(this, State.RIGHT);
					}
					leftChopstick.putDown(this, State.LEFT);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getCounter() {
		return eatingCounter;
	}

	private void think() throws InterruptedException {
		System.out.println(this + " is thinking...");
		this.eatingCounter++;
		Thread.sleep(random.nextInt(1000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " is eating...");
		Thread.sleep(random.nextInt(1000));
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
