package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Pow_50 {
	
	/** Iterative Solution! Linear - TLE */
	
	public double myPow(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		double ans = 1;
		for (long i = 0; i < N; i++)		// 做乘法
			ans = ans * x;
		return ans;
	}
	
	/** Fast Power Algorithm Recursive, Accept */
	
	public double myPow3(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}

		return fastPow(x, N);
	}
	private double fastPow(double x, long n) {
		if (n == 0)
			return 1.0;
		
		double half = fastPow(x, n / 2);
		if (n % 2 == 0)
			return half * half;
		else
			return half * half * x;
	}
	
	/** Fast Power Algorithm Iterative, AC */
	
	public double myPow4(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		double ans = 1;
		double tempProduct = x;		// 中间节点，初始化为底数x
		
		for (long i = N; i > 0; i /= 2) {	// 对N除2直到为0
			if ((i % 2) == 1)		// 若是奇数，则先乘一个成为偶数
				ans = ans * tempProduct;
			
			tempProduct = tempProduct * tempProduct;
		}
		
		return ans;
	}
	
	/** 指数是最小值的时候通不过test case */
	
	public double myPow2(double x, int n) {
		if (n == 0) 		// Base case
			return 1;
		if (n < 0) { 	// Negative case, transform to traditional case
			n = -n;
			x = 1 / x;
		}

		return (n % 2 == 0) ? myPow2(x * x, n / 2) : x * myPow2(x * x, n / 2);
	}
}
