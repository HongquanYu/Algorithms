package StudentLibrarySimulation;

import java.util.Random;

/** @author: Hongquan Yu
 *  @date: Feb 17, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Student implements Runnable {
	private int id;
	private Book[] books;
	
	public Student(int id, Book[] books) {
		this.id = id;
		this.books = books;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		
		while (true) {
			int bookid = random.nextInt(Constants.NUMBER_OF_BOOKS);
			
			try {
				books[bookid].read(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return "Student #" + id; 
	}
}
