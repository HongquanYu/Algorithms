package dp;

import java.util.Stack;

public class LongestValidPatentheses_32 {
	
	// 
	
	public boolean isValid(String s) {
		
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else if (!stack.empty() && stack.peek() == '(') {
				stack.pop();
			} else {
				return false;
			}
		}
		
		return stack.empty();
	}

	public int longestValidParentheses(String s) {
		
		int maxlen = 0;
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 2; j <= s.length(); j += 2) {
				if (isValid(s.substring(i, j)))
					maxlen = Math.max(maxlen, j - i);
			}
		}
		
		return maxlen;
	}
	
	// DP Solution. dp[i] = length of valid parenthesis so far to i
	
	public int longestValidParentheses2(String s) {

		int open = 0, max = 0, n = s.length();
		int[] dp = new int[n];

		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(')		// open to count the number of open parenthesis
				open++;
			if (s.charAt(i) == ')' && open > 0) {		// matches found
				dp[i] = 2 + dp[i - 1];						// add matches from previous
				if (i - dp[i] > 0)			// v[i]只是当前的valid 括号长度，得加上之前的valid长度
					dp[i] += dp[i - dp[i]];
				open--;
			}
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}
}
