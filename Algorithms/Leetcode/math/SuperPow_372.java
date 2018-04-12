package math;

/** @author: Hongquan Yu
 *  @date: Mar 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SuperPow_372 {
	
	/** Solution According to this Knowledge: ab % k = (a % k) * (b % k) % k 
	 * Since the power here is an array, we’d better handle it digit by digit.
	 * 
	 * One observation:
	 * a^1234567 % k = (a^1234560 % k) * (a^7 % k) % k = (a^123456 % k)^10 % k * (a^7 % k) % k
	 * 
	 * Looks complicated? Let me put it other way:
	 * Suppose f(a, b) calculates a^b % k; Then translate above formula to using f :
	 * f(a,1234567) = f(a, 1234560) * f(a, 7) % k = f(f(a, 123456),10) * f(a,7)%k;
	 * 
	 * Implementation of this idea:  */
	
	public int superPow(int a, int[] b) {
		return superPow(a, b, b.length, 1337);
	}
	
	/**  */
	
	private int superPow(int a, int[] b, int length, int mod) {
		if (length == 1)
			return powMod(a, b[0], mod);

		return powMod(superPow(a, b, length - 1, mod), 10, mod) * powMod(a, b[length - 1], mod) % mod;
	}

	/** Compute x^y mod k */
	
	private int powMod(int x, int y, int mod) {
		x %= mod;
		int pow = 1;
		for (int i = 0; i < y; i++)
			pow = (pow * x) % mod;
		
		return pow;
	}
}
