package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindTheClosestPalindrome_564 {
	public String nearestPalindromic(String n) {
		long num = Long.parseLong(n);
		for (long i = 1;; i++) {
			if (isPalindrome(num - i))
				return "" + (num - i);
			if (isPalindrome(num + i))
				return "" + (num + i);
		}
	}

	boolean isPalindrome(long x) {
		long t = x, rev = 0;
		while (t > 0) {
			rev = 10 * rev + t % 10;
			t /= 10;
		}
		return rev == x;
	}
}
