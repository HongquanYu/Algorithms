package math;

/** @author: Hongquan Yu
 *  @date: Feb 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReverseInteger_7 {
	
	/** Not accept test case:
	 * 1534236469, expected: 0, actually: 1056389759 */
	
	public static int reverse(int x) {
		int res = 0;

		while (x != 0) {
			int tail = x % 10;
			int tmp = res * 10 + tail;
			if ((tmp - tail) / 10 != res)	// detect for overflows
				return 0;
			res = tmp;
			x = x / 10;
		}

		return res;
	}
	
	public static int reverse1(int x) {
		int res = 0;

		while (x != 0) {
			int t = x % 10;
			res = (res * 10) + t;
			x /= 10;
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		int t = 1534236469;		// expected: 0
		System.out.println(reverse1(t));
		System.out.println(reverse(t));
	}
}
