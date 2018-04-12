package Hyland;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class ReducedFractionSum {
	static String[] reducedFractionSums(String[] expressions) {
		int N = expressions.length;
		String[] res = new String[N];
		int ptr = 0;
		int num1, num2, den1, den2;
		for (int i = 0; i < N; ++i) {
			String[] nums = expressions[i].split("\\+");
			String[] ops1 = nums[0].split("\\/");
			num1 = Integer.parseInt(ops1[0]);
			den1 = Integer.parseInt(ops1[1]);

			String[] ops2 = nums[1].split("\\/");
			num2 = Integer.parseInt(ops2[0]);
			den2 = Integer.parseInt(ops2[1]);

			int den3 = gcd(den1, den2);
			den3 = (den1 * den2) / den3;
			int num3 = num1 * (den3 / den1) + num2 * (den3 / den2);
			res[ptr++] = reduce(num3, den3);
		}
		return res;
	}
	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	private static String reduce(int a, int b) {
		int g = gcd(a, b);
		return a / g + "/" + b / g;
	}
}
