package dp;

public class CountingBits_338 {
	
	/** #1 Pop Count
	 *  O(kn) */
	
	public int[] countBits1(int num) {
		
		int[] ans = new int[num + 1];
		
		for (int i = 0; i <= num; ++i)
			ans[i] = popcount(i);
		
		return ans;
	}

	private int popcount(int x) {
		
		int count;
		
		for (count = 0; x != 0; ++count)
			x &= x - 1; // zeroing out the least significant nonzero bit
		
		return count;
	}
	
	/** #2 DP + Most Significant Bit
	 *  P(x + b) = P(x) + 1, b = 2^m > x */
	
	public int[] countBits2(int num) {
		
		int[] ans = new int[num + 1];
		int i = 0, b = 1;
		
		// [0, b) is calculated
		while (b <= num) {
			
			// generate [b, 2b) or [b, num) from [0, b)
			while (i < b && i + b <= num) {
				ans[i + b] = ans[i] + 1;
				++i;
			}
			
			i = 0; 		// reset i
			b <<= 1; 	// b = 2b
		}
		
		return ans;
	}
	
	/** #3 DP + Least Significant Bit
	 *  P(x) = P(x / 2) + (x mod 2) */
	
	public int[] countBits3(int num) {
		
		int[] ans = new int[num + 1];
		
		for (int i = 1; i <= num; ++i)
			ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
		
		return ans;
	}
	
	/** #4 DP + Last Set Bit
	 *  P(x) = P(x & (x − 1)) + 1 */
	
	public int[] countBits4(int num) {
		
		int[] ans = new int[num + 1];
		
		for (int i = 1; i <= num; ++i)
			ans[i] = ans[i & (i - 1)] + 1;
		
		return ans;
	}
}
