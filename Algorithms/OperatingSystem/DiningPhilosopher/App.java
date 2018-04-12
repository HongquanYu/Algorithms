package DiningPhilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** @author: Hongquan Yu
 *  @date: Feb 17, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = null;		// 线程操作管理对象
		Philosopher[] philosophers = null;			// 哲学家们
		Chopstick[] chopsticks = null;
		
		try {
			// 哲学家 和 筷子 们初始化
			philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHER];
			chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
			
			for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; ++i) 
				chopsticks[i] = new Chopstick(i);
			
			// 建立一个线程池
			executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHER);
			
			// 对每个哲学家进行初始化 并 开始先赢的线程
			for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHER; ++i) {
				philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
				executorService.execute(philosophers[i]);
			}
			
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
			
			for (Philosopher p : philosophers)		// 每个哲学家都吃饱了
				p.setFull(true);
			
		} finally {		// 关掉服务，并清理现场
			executorService.shutdown();
			
			while(!executorService.isTerminated())
				Thread.sleep(1000);
			
			for (Philosopher p : philosophers)
				System.out.println(p + " eats " + p.getCounter() + " times.");
		}
	}
}
