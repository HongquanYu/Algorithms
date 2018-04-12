package dp;

/** 72. Edit Distance. String alignment
 * 
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character 
 * b) Delete a character 
 * c) Replace a character */

public class EditDistance_72 {
	
	/** dp[i][j]: the minimum number of operations to convert word1[0..i - 1] to word2[0..j - 1]
	 * 
	 * dp[i][0] = i
	 * dp[0][j] = j
	 * dp[i][j] = dp[i - 1][j - 1],	if word1[i - 1] = word2[j - 1]
	 * dp[i][j] = min(dp[i - 1][j - 1] + 1,		-- replacement last letter of word1 and word2
	 *  				dp[i - 1][j] + 1, 		-- delete word1[i-1]. word1[i-2] = word2[j-1]
	 *  				dp[i][j - 1] + 1) 		-- insert word2[j-1] to word1[i-1] */
	
    public int minDistance(String word1, String word2) {
    	
		int m = word1.length(), n = word2.length();

		int[][] dp = new int[m + 1][n + 1];
		
		// initialize corner cases
		for (int i = 1; i <= m; i++)
			dp[i][0] = i; 
		for (int j = 1; j <= n; j++)
			dp[0][j] = j;

		for (int i = 1; i <= m; i++) { 				// traverse word1
			for (int j = 1; j <= n; j++) { 			// traverse word2
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) // last letter is equal
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
			}
		}
		
		return dp[m][n];
    }
}
