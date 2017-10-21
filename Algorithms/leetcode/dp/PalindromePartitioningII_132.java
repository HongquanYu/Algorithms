package dp;

public class PalindromePartitioningII_132 {
	
	public int minCutDP(String s) {
		if (s.isEmpty())
			return 0;
		int n = s.length();
		int[] dp = new int[n];
		boolean[][] isPalindrome = new boolean[n][n];

		for (int right = 0; right < s.length(); right++) {
			dp[right] = right;
			isPalindrome[right][right] = true;
			for (int left = 0; left <= right; left++) {
				if (s.charAt(left) == s.charAt(right)
						&& (right - left <= 1 || isPalindrome[left + 1][right - 1])) {
					if (left == 0)
						dp[right] = 0;
					else {
						isPalindrome[left][right] = true;
						dp[right] = Math.min(dp[left - 1] + 1, dp[right]);
					}
				}
			}
		}
		return dp[n - 1];
	}
	
	public int minCut(String s) {
		int n = s.length();
		int[] cut = new int[n + 1]; // number of cuts for the first k characters
		for (int i = 0; i <= n; i++)
			cut[i] = i - 1;
		for (int i = 0; i < n; i++) {
			// odd length palindrome
			for (int j = 0; i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j); j++)
				cut[i + j + 1] = Math.min(cut[i + j + 1], 1 + cut[i - j]);

			// even length palindrome
			for (int j = 1; i - j + 1 >= 0 && i + j < n && s.charAt(i - j + 1) == s.charAt(i + j); j++)
				cut[i + j + 1] = Math.min(cut[i + j + 1], 1 + cut[i - j + 1]);
		}
		
		return cut[n];
	}
}
