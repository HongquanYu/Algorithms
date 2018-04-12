package dp;

public class RegularExpressionMatching_10 {
	
	/* The recursion solution is pretty straightforward.
	 * The key is to find that if a star is present in the pattern, it will be the second position of
	 * pattern.
	 * Then, we may ignore this part of pattern, or deleting a matching character in the text.
	 * If we have a match on the remaining strings after any of these operations, then the initial
	 * input matched.  */
	public boolean isMatch(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();
		
		/* The first_match solves following one to one relationships:
		 * 1, 'a' - 'a': same character mapping
		 * 2, 'a'  - '.' mapping 
		 * The mapping relationship will also be used at the middle of strings
		 * as recursion going deep so it is a base case */
		
		boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
		
		/* Reduction */
		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {		// pattern第二个字符是*，那么
			return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
		} else {
			return first_match && isMatch(text.substring(1), pattern.substring(1));
		}
	}
	
	/* DP solution 
	 * dp(i, j): does text[0, i] matches pattern[0, j]?
	 * */
	
	
	/* Top-down DP solution */
	
	enum Result { TRUE, FALSE }

	Result[][] memo;

	public boolean isMatch2(String text, String pattern) {
		memo = new Result[text.length() + 1][pattern.length() + 1];
		return dp(0, 0, text, pattern);
	}

	public boolean dp(int i, int j, String text, String pattern) {
		if (memo[i][j] != null) {
			return memo[i][j] == Result.TRUE;
		}
		
		boolean ans;
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
			} else {
				ans = first_match && dp(i + 1, j + 1, text, pattern);
			}
		}
		memo[i][j] = ans ? Result.TRUE : Result.FALSE;
		return ans;
	}
	
	/* Bottom-Up Variation */
	
	public boolean isMatch3(String text, String pattern) {
		boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
		dp[text.length()][pattern.length()] = true;

		for (int i = text.length(); i >= 0; i--) {
			for (int j = pattern.length() - 1; j >= 0; j--) {
				boolean first_match = (i < text.length()
						&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
				if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
					dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
				} else {
					dp[i][j] = first_match && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}
}
