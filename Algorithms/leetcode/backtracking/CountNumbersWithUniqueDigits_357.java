package backtracking;

public class CountNumbersWithUniqueDigits_357 {
	
	public static int countNumbersWithUniqueDigits(int n) {
		if (n > 10) {
			return countNumbersWithUniqueDigits(10);
		}
		int count = 1; // x == 0
		long max = (long) Math.pow(10, n);

		boolean[] used = new boolean[10];

		for (int i = 1; i < 10; i++) {
			used[i] = true;
			count += search(i, max, used);
			used[i] = false;
		}

		return count;
	}

	private static int search(long prev, long max, boolean[] used) {
		int count = 0;
		if (prev < max) {
			count += 1;
		} else {
			return count;
		}

		for (int i = 0; i < 10; i++) {
			if (!used[i]) {
				used[i] = true;
				long cur = 10 * prev + i;
				count += search(cur, max, used);
				used[i] = false;
			}
		}

		return count;
	}
	
	public int countNumbersWithUniqueDigits2(int n) {
		if (n == 0)
			return 1;

		int[] dp = new int[n + 1];
		dp[1] = 10;
		int N = n;
		int p = 2;
		int base = 9;
		int digits = 9;
		while (N > 1) {
			base = base * digits;
			dp[p] = dp[p - 1] + base;

			digits--;
			N--;
			p++;
		}

		return dp[n];
	}

	public int countNumbersWithUniqueDigits_dp(int n) {
		if (n == 0)
			return 1;

		int res = 10;
		int uniqueDigits = 9;
		int availableNumber = 9;
		while (n-- > 1 && availableNumber > 0) {
			uniqueDigits = uniqueDigits * availableNumber;
			res += uniqueDigits;
			availableNumber--;
		}
		
		return res;
	}
}
