package ReaderWriter;

/** @author: Hongquan Yu
 *  @date: Feb 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Database {
	private int readers; // number of active readers

	public Database() {
		this.readers = 0;
	}
	
	// 读操作不需要
	public void read(int number) {
		synchronized (this) {		// 开始读操作，增加计数器
			this.readers++;
			System.out.println("Reader " + number + " starts reading.");
		}

		try {
			Thread.sleep((int) (Math.random() * 2000));
		} catch (InterruptedException e) { }

		synchronized (this) {		// 读完成，离开
			System.out.println("Reader " + number + " stops reading.");
			this.readers--;
			
			if (this.readers == 0) {
				System.out.println("---------- 所有读者均已读完，可以执行写操作 ----------");
				this.notifyAll();
			}
		}
	}
	
	// 写操作需要等没有人读的时候才能进行
	public synchronized void write(int number) {
		while (this.readers != 0) {			// 还有读者在读操作，就wait
			try {
				this.wait();
			} catch (InterruptedException e) { }
		}
		System.out.println("Writer " + number + " starts writing.");

		try {
			Thread.sleep((int) (Math.random() * 2000));
		} catch (InterruptedException e) { }

		System.out.println("Writer " + number + " stops writing.");
		this.notifyAll();
	}
}
