package dp;

public class DecodeWays1_91 {
	
	/** dp[i] = dp[i - 1] + dp[i-2]. If [i - 1, i] <= 26
	 *  when we move forward, we skip all '0's for it is meaningless of '0' in front of substring
	 *  if the current letter and the previous letter formed a digit less than 26, then there must
	 *  two ways to deal with current letter. Therefore dp[i] = dp[i - 1] + dp[i-2]. If it is 
	 *  larger than 26, then the only one way to decode so the substring from first to current 
	 *  letter should be dp[n] = dp[n-1] */
	
	public int numDecodings1(String s) {
		int N = s.length();
		if (N == 0) 	return 0;

		int[] dp = new int[N + 1];
		dp[N] = 1;
		dp[N - 1] = s.charAt(N - 1) != '0' ? 1 : 0;
		
		// skip '0's, it's safe because we are moving forwards. Front '0' makes no meanings
		for (int i = N - 2; i >= 0; i--) {
			if (s.charAt(i) != '0') {
				if (Integer.parseInt(s.substring(i, i + 2)) <= 26)
					dp[i] = dp[i + 1] + dp[i + 2];
				else
					dp[i] = dp[i + 1];
			}
		}

		return dp[0];
	}
	
	/** I used a dp array of size n + 1 to save subproblem solutions. dp[0] means
	 * an empty string will have one way to decode, dp[1] means the way to
	 * decode a string of size 1. I then check one digit and two digit
	 * combination and save the results along the way. In the end, dp[n] will be
	 * the end result. */
	
	public int numDecodings2(String s) {
		if (s == null || s.length() == 0)
			return 0;
		
		int N = s.length();
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) != '0' ? 1 : 0;
		
		for (int i = 2; i <= N; i++) {
			int one = Integer.parseInt(s.substring(i - 1, i));
			int two = Integer.valueOf(s.substring(i - 2, i));
			
			if (one >= 1 && one <= 9)	// 当前位可以作为单独一个存在
				dp[i] += dp[i - 1];
			
			if (two >= 10 && two <= 26)	// 当前位可以和前一位连起来
				dp[i] += dp[i - 2];
		}
		
		return dp[N];
	}
	
	/** 优化成常数空间 */
	
	public int numDecodings(String s) {
		if (s.length() == 0 || s.charAt(0) == '0')
			return 0;
		int N = s.length();
		int pre = 1, cur = 1;
		for (int i = 1; i < N; ++i) {
			if (s.charAt(i) == '0')
				cur = 0;
			int num = Integer.parseInt(s.substring(i - 1, i + 1));
			if (num < 10 || num > 26)
				pre = 0;
			
			cur = cur + pre;		// 这两步是更新 cur，pre 存 old cur！
			pre = cur - pre;
		}
		return cur;
	}
}
