package dp;

public class FourKeysKeyboard_651 {
	public int maxA(int n) {
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = i;
			for (int j = 1; j <= i - 3; j++)
				dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
		}
		return dp[n];
	}

	public int maxA1(int n) {
		int max = n;
		for (int i = 1; i <= n - 3; i++)
			max = Math.max(max, maxA(i) * (n - i - 1));
		return max;
	}
}
