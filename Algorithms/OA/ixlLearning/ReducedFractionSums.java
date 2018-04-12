package ixlLearning;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class ReducedFractionSums {
	public String[] reducedFractionSums(String[] expressions) {
		if (expressions == null || expressions.length == 0)
			return new String[0];

		String[] res = new String[expressions.length];
		for (int i = 0; i < expressions.length; ++i) {
			String[] expr = expressions[i].split("[+/]");
			if (expr.length > 0) {
				int n1 = Integer.valueOf(expr[0]);
				int d1 = Integer.valueOf(expr[1]);
				int n2 = Integer.valueOf(expr[2]);
				int d2 = Integer.valueOf(expr[3]);

				int numerator = n1 * d2 + d1 * n2;
				int denominator = d1 * d2;

				int gcd = GCD(numerator, denominator);
				res[i] = numerator / gcd + "/" + denominator / gcd;
			}
		}
		return res;
	}
	
	/** 下面这个找 GCD 的方法通过不了 Hackerrank，原因是 g 返回 0
	 * 已解决：通过不了是因为当时的算法搞错了！ */
	
	private int GCD(int m, int n) {
		return n == 0 ? m : GCD(n, m % n);
	}
	
	private int gcd(int m, int n) {
		int a = 0, b = 0;

		a = m > n ? m : n;
		b = (a == m) ? n : m;

		int temp = 0;
		while (a % b != 0) {
			temp = a % b;
			a = b;
			b = temp;
		}
		return b;
	}
	
	public static void main(String[] args) {
		String[] tmp = new String[] {"722/148+360/176", 	
									"978/1212+183/183",
									"358/472+301/417", 
									"780/309+684/988", 
									"258/840+854/686" };
		// Expected Answer Should Be: 
		/* 2818/407 
		 * 365/202 
		 * 145679/98412 
		 * 4307/1339 
		 * 1521/980 */
		
		ReducedFractionSums r = new ReducedFractionSums();
		
		System.out.println(Arrays.toString(r.reducedFractionSums(tmp)));
	}
}
