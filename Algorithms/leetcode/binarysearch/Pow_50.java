package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Pow_50 {
	public double pow(double x, int n) {
		if (n == 0) 		// Base case
			return 1;
		if (n < 0) { 	// Negative case, transform to traditional case
			n = -n;
			x = 1 / x;
		}

		return (n % 2 == 0) ? pow(x * x, n / 2) : x * pow(x * x, n / 2);
	}
}
