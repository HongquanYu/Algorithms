package linkedlist;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class HappyNumber_202 {
	public boolean isHappy(int n) {
		Set<Integer> inLoop = new HashSet<Integer>();
		int squareSum, remain;
		while (inLoop.add(n)) {
			squareSum = 0;
			while (n > 0) {
				remain = n % 10;
				squareSum += remain * remain;
				n /= 10;
			}
			if (squareSum == 1)
				return true;
			else
				n = squareSum;
		}
		
		return false;
	}
	
	public boolean isHappy1(int n) {
		int slow, fast;
		slow = fast = n;
		do {
			slow = digitSquareSum(slow);
			fast = digitSquareSum(fast);
			fast = digitSquareSum(fast);
		} while (slow != fast);
		
		if (slow == 1)
			return true;
		else
			return false;
	}

	private int digitSquareSum(int n) {
		int sum = 0, tmp;
		while (n > 0) {
			tmp = n % 10;
			sum += tmp * tmp;
			n /= 10;
		}
		return sum;
	}
}
