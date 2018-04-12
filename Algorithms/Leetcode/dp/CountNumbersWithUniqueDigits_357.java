package dp;

public class CountNumbersWithUniqueDigits_357 {
	public int countNumbersWithUniqueDigits(int n) {
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
