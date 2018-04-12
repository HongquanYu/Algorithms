package ReaderWriter;

/** @author: Hongquan Yu
 *  @date: Feb 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 在这个程序里最重要的是：
 * 读和写操作（即增加减少当前在读的人数）必须是synchronized的，也就是critical section
 * 这里必须只有一个人进行操作，不然就会发生不可预测的行为 */

public class ReaderWriterTest {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println( "Usage: java Simulator <number of readers> <number of writers>");
		} else {
			final int READERS = Integer.parseInt(args[0]);
			final int WRITERS = Integer.parseInt(args[1]);
			
			Database database = new Database();	// 这个文件供所有人读写
			
			for (int i = 0; i < READERS; i++)	// 创造指定个数读者
				new Reader(database).start();
			
			for (int i = 0; i < WRITERS; i++)	// 创造指定个数写者
				new Writer(database).start();
			
		}
	}
}
