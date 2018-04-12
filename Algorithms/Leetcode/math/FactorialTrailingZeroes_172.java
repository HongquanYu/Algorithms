package math;

/** @author: Hongquan Yu
 *  @date: Feb 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FactorialTrailingZeroes_172 {
	
	/**  */
	
	public int trailingZeroes(int n) {
		return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
	}
	
	// 
	public int trailingZeroes2(int n) {
		int ans = 0;
		while (n >= 5) {
			n /= 5;
			ans += n;
		}
		return ans;
	}
}
