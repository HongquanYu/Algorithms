package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SqrtX_69 {
	
	/* Use binary search to search the number sqrt foot */
	
	public int mySqrt(int x) {
		if (x == 0)
			return 0;

		int left = 1, right = Integer.MAX_VALUE;

		while (true) {
			int mid = left + (right - left) / 2;
			if (mid > x / mid) {		// mid is large than square foot
				right = mid - 1;
			} else {					// mid is less and equal than square foot
				if (mid + 1 > x / (mid + 1))		// next is large than square foot
					return mid;
				left = mid + 1;
			}
		}
	}
}
