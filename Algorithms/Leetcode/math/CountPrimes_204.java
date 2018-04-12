package math;

public class CountPrimes_204 {
	
	/** 用一个辅助数组来存当前值是否是素数，发现一个素数，将其所有倍数都设为非素数 */
	
	public int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];		// 存的都不是素数
		int count = 0;
		
		for (int i = 2; i < n; i++) {
			if (notPrime[i] == false) {			// 当前为素数
				count++;
				for (int j = 2; i * j < n; j++)	// 素数的倍数都设为非素数
					notPrime[i * j] = true;
			}
		}

		return count;
	}
}
