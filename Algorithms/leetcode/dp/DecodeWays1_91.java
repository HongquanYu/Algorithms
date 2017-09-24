package dp;

public class DecodeWays1_91 {
	
	/** dp[i] = dp[i - 1] + dp[i-2]. If [i - 1, i] <= 26
	 *  when we move forward, we skip all '0's for it is meaningless of '0' in front of substring
	 *  if the current letter and the previous letter formed a digit less than 26, then there must
	 *  two ways to deal with current letter. Therefore dp[i] = dp[i - 1] + dp[i-2]. If it is 
	 *  larger than 26, then the only one way to decode so the substring from first to current 
	 *  letter should be dp[n] = dp[n-1]
	 *  */
	
	public int numDecodings1(String s) {
		
		int n = s.length();
		
		if (n == 0)
			return 0;

		int[] dp = new int[n + 1];		// dp arrays
		dp[n] = 1;						// position holder
		dp[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;		// the first character

		for (int i = n - 2; i >= 0; i--)		// From tail to front
			if (s.charAt(i) == '0')				// skip '0's, it's safe because we are moving forwards. Front '0' makes no meanings
				continue;
			else								// dp[i] = dp[i - 1] + dp[i-2]. If [i - 1, i] <= 26
				dp[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? dp[i + 1] + dp[i + 2] : dp[i + 1];

		return dp[0];
	}
	
	/* I used a dp array of size n + 1 to save subproblem solutions. dp[0] means
	 * an empty string will have one way to decode, dp[1] means the way to
	 * decode a string of size 1. I then check one digit and two digit
	 * combination and save the results along the way. In the end, dp[n] will be
	 * the end result. */
	
	public int numDecodings2(String s) {
		
		if (s == null || s.length() == 0)
			return 0;
		
		int len = s.length();
		int[] dp = new int[len + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) != '0' ? 1 : 0;
		
		for (int i = 2; i <= len; i++) {
			
			int first = Integer.valueOf(s.substring(i - 1, i));
			int second = Integer.valueOf(s.substring(i - 2, i));
			
			if (first >= 1 && first <= 9)	// firstly the the ith char should be equal to previous solution
				dp[i] += dp[i - 1];
			
			if (second >= 10 && second <= 26)	// if it is in the range of 0 - 26, then we add another set of ways
				dp[i] += dp[i - 2];
			
		}
		
		return dp[len];
	}
	
	// ---------------------------- 639. Decode Ways II ---------------------------------
	
    public int numDecodings3(String s) {
		

		return 0;
    }
}
