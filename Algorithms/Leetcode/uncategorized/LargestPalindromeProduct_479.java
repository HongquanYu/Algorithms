package uncategorized;

/** @author: Hongquan Yu
 *  @date: Mar 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class LargestPalindromeProduct_479 {
	
	/** 思想是：
	 *  */
	
	public int largestPalindrome(int n) {
		if (n == 1) 	return 9;
		
		long upperBound = (long) Math.pow(10, n) - 1;
		long lowerBound = upperBound / 10 + 1;
		
		long t = 0;
		for (long num = upperBound - 1; num >= lowerBound; num--) {
			t = buildPalindrome(num);
			if (isFactorable(t, upperBound, lowerBound))
				return (int) (t % 1337);
		}
		
		return -1;
	}

	private long buildPalindrome(long num) {
		long res = num;
		while (num > 0) {
			res = res * 10 + num % 10;
			num /= 10;
		}
		return res;
	}

	public boolean isFactorable(long num, long upperBound, long lowerBound) {
		long mid = (long) Math.sqrt(num);
		
		if (mid > upperBound || mid < lowerBound)
			return false;
		
		long low = mid, high = mid, t = low * high;
		
		while (t != num) {
			if (t < num) {
				if (++high > upperBound)
					return false;
			} else {
				if (--low < lowerBound)
					return false;
			}
			t = low * high;
		}
		
		return true;
	}
	
	
	/** Brute Force Solution
	 * 直接在上下区间里搜索 */
	
	public int largestPalindrome2(int n) {
		if (n >= 9)	return -1;
		
		int upperBound = 0, lowerBound = 0;
		long maxProduct = 0;

		for (int i = 1; i <= n; i++) {
			upperBound *= 10;
			upperBound += 9;
		}
		lowerBound = 1 + upperBound / 10;

		for (int i = upperBound; i >= lowerBound; i--) {
			for (int j = i; j >= lowerBound; j--) {
				long product = (long)i * j;

				if (product < maxProduct)
					break;
				
				long number = product;
				long reverse = 0;
				
				while (number != 0) {
					reverse = reverse * 10 + number % 10;
					number /= 10;
				}

				if (product == reverse && product > maxProduct)
					maxProduct = product;
			}
		}
		
		return (int)(maxProduct % 1337);
	}
	
	public static void main(String[] args) {
		LargestPalindromeProduct_479 l = new LargestPalindromeProduct_479();
		
		System.out.println(l.largestPalindrome(1));
		System.out.println(l.largestPalindrome(2));
		System.out.println(l.largestPalindrome(3));
		System.out.println(l.largestPalindrome(4));
		System.out.println(l.largestPalindrome(5));
		System.out.println(l.largestPalindrome(6));
		System.out.println(l.largestPalindrome(7));
		System.out.println(l.largestPalindrome(8));
	}
}
