package ReaderWriter;

/** @author: Hongquan Yu
 *  @date: Feb 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Writer extends Thread {
	private static int writers = 0; 	// number of writers

	private int number;		// 当前writer
	private Database database;

	public Writer(Database database) {
		this.database = database;
		this.number = Writer.writers++;		// 顺序新加入一个写者
	}

	public void run() {
		while (true) {
			final int DELAY = 5000;
			try {
				Thread.sleep((int) (Math.random() * DELAY));
			} catch (InterruptedException e) { }
			
			this.database.write(this.number);	// 当前writer执行写操作
		}
	}
}