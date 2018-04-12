package array;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Interval {
	public int start;
	public int end;
	public Interval() {
		start = 0;
		end = 0;
	}
	public Interval(int s, int e) {
		start = s;
		end = e;
	}
	
	public String toString() {
		return "[" + this.start + ", " + this.end + "]";
	}
}
