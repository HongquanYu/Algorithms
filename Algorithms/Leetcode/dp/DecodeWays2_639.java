package dp;

public class DecodeWays2_639 {
	
	private final int M = 1000000007;
	
	/******************************** DP 解法：O(n) Space ***********************************
	 * dp[i] number of ways to decode up to char i(s[0, i])
	 * dp[0] = 1
	 * dp[1] = 0, if s[0] == '0'
	 * 		 = 9, if s[0] == '*'
	 * 		 = 1, otherwise
	 * 
	 *  dp[i] = c(s[i]) * dp[i - 1] + C(s[i-1], s[i]) * dp[i - 2]
	 *  dp[i] %= 1000000007 */
	
	public int numDecodings(String s) {
		int N = s.length();
		long[] dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
		
		for (int i = 1; i < N; i++) {
			char pre = s.charAt(i - 1), cur = s.charAt(i);
			
			if (cur == '*') {
				dp[i + 1] = 9 * dp[i];	// s[i] 单独 decode 可以有 9 种，下面是合起来 decode
				if 		(pre == '1') 	dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;		// 11 到 19 共 9 种
				else if 	(pre == '2') 	dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;		// 21 到 26 共 6 种
				else if 	(pre == '*') 	dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;	// 第一位 7 种，第二位可以有 9 种
			} else {		// 当前位是 0 到 9 中任意确定的一个！
				dp[i + 1] = cur != '0' ? dp[i] : 0;	// s[i] 单独 decode, 下面是合起来 decode
				if 		(pre == '1') 				dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;		// 只有一种，10 到 19 确定的一个数
				else if 	(pre == '2' && cur <= '6') 	dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;		// 只有一种，20 到 26 确定的一个数
				else if 	(pre == '*') 				dp[i + 1] = (dp[i + 1] + (cur <= '6' ? 2 : 1) * dp[i - 1]) % M;	// 当前少于6可以组成 20 到 26 的一个新组合
			}
		}
		return (int) dp[N];
	}
	
	/********************************** DP 解法：Constant Space ***********************************
	 * 简化上面的 equation，因为我们在任何时候都只用到了 2 个，所以可以只用两个变量来保存 */
	
	public int numDecodingsConstant(String s) {
		long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
		for (int i = 1; i < s.length(); i++) {
			long temp = second;
			char pre = s.charAt(i - 1), cur = s.charAt(i);
			
			if (cur == '*') {
				second = 9 * second;
				if 		(pre == '1') 	second = (second + 9 * first) % M;
				else if 	(pre == '2') 	second = (second + 6 * first) % M;
				else if 	(pre == '*') 	second = (second + 15 * first) % M;
			} else {
				second = cur != '0' ? second : 0;
				if 		(pre == '1') 				second = (second + first) % M;
				else if 	(pre == '2' && cur <= '6') 	second = (second + first) % M;
				else if 	(pre == '*') 				second = (second + (cur <= '6' ? 2 : 1) * first) % M;
			}
			first = temp;
		}
		return (int) second;
	}
}
