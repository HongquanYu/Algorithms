package math;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class AddDigits_258 {
	
	/** Digit Root:
	 * 1, dr(n) = 0 					if n == 0
	 * 2, dr(n) = (b-1) 				if n != 0 and n % (b-1) == 0 
	 * 3, dr(n) = n mod (b-1) 		if n % (b-1) != 0 
	 * -------------------------------------------
	 * OR: dr(n) = 1 + (n - 1) % 9 */
	
	public int addDigits(int num) {
		int temp = num;
		int sum = 0;

		while (true) {
			while (temp != 0) {			// 所有位数累加
				sum = sum + temp % 10;
				temp = temp / 10;
			}

			if (sum / 10 == 0) {			// 当前数是一位数
				return sum;
			} else {						// 再循环去加
				temp = sum;
				sum = 0;
			}
		}
	}
	
	public int addDigits1(int num) {
		int res = 0;
		
		while (num > 0) {
			res += num % 10;
			if (res >= 10)
				res = res % 10 + res / 10;
			
			num = num / 10;
		}
		
		return res;
	}
	
	/** Recursion */
	
	public int addDigits3(int num) {
		int result = 0;
		if (num < 10)
			return num;
		while (num / 10 >= 1) {
			result += num % 10;
			num = num / 10;
		}
		result += num;
		return addDigits3(result);
	}
	
	/** O(1) */
	
	public int addDigits2(int num) {
		return 1 + (num - 1) % 9;
	}
	
	public int addDigits5(int num) {
		if ( num == 0)
			return num;
		else if (num % 9 == 0)
			return 9;
		else
			return num % 9;
	}
}
