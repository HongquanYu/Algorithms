package dp;

public class DistinctSubsequences_115 {
	
	/* 	dp[i][j] = number of distinct subsequences S(i) contains which equal to T(j)
	 *   		 | = dp[i-1]][j-1] + dp[i][j-1]. 	S(i) == T(j)
	 *  dp[i][j] |
	 *  		 | = dp[i][j-1]. 					S(i) != T(j)
	 *  
	 *  If length of S < T, then dp[i][j] = 0
	 *  We only can backtrack S, not T which is the pattern we need to meet
	 */
	
	public int numDistinct(String S, String T) {
		
		int[][] dp = new int[T.length() + 1][S.length() + 1];

		// filling the first row: with 1s
		for (int j = 0; j <= S.length(); j++) {
			dp[0][j] = 1;
		}

		// the first column is 0 by default in every other rows but the first, which we need.

		for (int i = 1; i <= T.length(); i++) {
			for (int j = 1; j <= S.length(); j++) {
				if (T.charAt(i - 1) == S.charAt(j - 1)) { 		// Last characters are equal
					dp[i][j] = dp[i - 1][j - 1] 				// - All substrings without last characters in both
							 + dp[i][j - 1];					// - All substrings without last character in S
				} else {										// Last characters are not equal
					dp[i][j] = dp[i][j - 1];					// - the value is same as the value without last character in S
				}
			}
		}

		return dp[T.length()][S.length()];
	}
}
