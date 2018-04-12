package bitManipulation;

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
			if ((n & 1) == 0) {	// Even number, divide itself two
				n >>>= 1;
			} else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {	// do a minus operation
				--n;
			} else {				// Do a plus operation
				++n;
			}
			++c;
		}
		
		return c;
	}
}
