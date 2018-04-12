package wayfair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class DivideEqually {
	private static void divide(int n, int k, int m) {
		divide(Collections.emptyList(), n, k, m, 1);
	}

	private static void divide(List<Integer> partial, int n, int k, int m, int min) {
		if ((k > 0)) {
			if ((n <= k * m) && (n >= k * min)) {
				for (int i = min; i <= Math.min(m, n); i++) {
					List<Integer> newPartial = new ArrayList<>(partial);
					newPartial.add(i);
					divide(newPartial, n - i, k - 1, m, i);
				}
			}
		} else if (n == 0) {		// Right solution
			System.out.println(partial);
		}
	}

	public static void main(String[] args) {
		divide(7, 4, 3);
	}
}
