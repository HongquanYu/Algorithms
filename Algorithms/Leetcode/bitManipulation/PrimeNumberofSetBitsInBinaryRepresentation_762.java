package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Apr 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class PrimeNumberofSetBitsInBinaryRepresentation_762 {
	
	/** 遍历区间并判断当前数的 bitCount 是不是 prime */
	
	public int countPrimeSetBits(int L, int R) {
		int ans = 0;
		for (int x = L; x <= R; ++x)
			if (isSmallPrime(Integer.bitCount(x)))
				ans++;
		return ans;
	}
	public boolean isSmallPrime(int x) {
		return (x == 2 || x == 3 || x == 5 || x == 7 || 
				x == 11 || x == 13 || x == 17 || x == 19);
	}
}
