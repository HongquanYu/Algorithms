package bitmanipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class IntegerReplacement_397 {
	public int integerReplacement(int n) {
		int c = 0;
		while (n != 1) {
			if ((n & 1) == 0) {
				n >>>= 1;
			} else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
				--n;
			} else {
				++n;
			}
			++c;
		}
		return c;
	}
}
