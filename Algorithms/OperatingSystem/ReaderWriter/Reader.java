package ReaderWriter;

/** @author: Hongquan Yu
 *  @date: Feb 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Reader extends Thread {
	private static int readers = 0; // number of readers

	private int number;
	private Database database;

	public Reader(Database database) {
		this.database = database;
		this.number = Reader.readers++;		// 顺序新加入一个读者
	}

	public void run() {
		while (true) {
			final int DELAY = 5000;
			try {
				Thread.sleep((int) (Math.random() * DELAY));
			} catch (InterruptedException e) { }
			
			this.database.read(this.number);		// 当前读者执行读操作
		}
	}
}
