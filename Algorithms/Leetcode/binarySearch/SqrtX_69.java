package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SqrtX_69 {
	
	/** Use binary search to search the number sqrt foot */
	
	public int mySqrt(int x) {
		if (x == 0)
			return 0;

		int left = 1, right = x;

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
	
	/** Newton Solution */
	
	public int sqrt(int x) {
		long r = x;
		while (r * r > x)
			r = (r + x / r) / 2;
		return (int) r;
	}
	
	/** Follow Up: What if the number is a double type?
	 * Precision = 0.000001 */
	
	public double sqrt(double x) {
		if (x == 0.0)
			return 0.0;
		double lo = 0.000001, hi = Double.MAX_VALUE;
		
		while (true) {
			double mid = lo + (hi - lo) / 2;
			if (mid > x / mid)
				hi = mid - 0.000001;
			else {
				if (mid + 0.000001 > x / (mid + 0.000001)) {
					mid = Math.round(mid * 1000000.0) / 1000000.0;	// 做一个roundup
					return mid;
				}
				lo = mid + 0.000001;
			}
		}
	}
	
	public static void main(String[] args) {
		double d = 32.49;
		double b = 33.628401;
		SqrtX_69 s = new SqrtX_69();
		
		System.out.println(s.sqrt(d));
		System.out.println(s.sqrt(b));
	}
}
