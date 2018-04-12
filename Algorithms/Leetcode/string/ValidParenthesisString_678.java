package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ValidParenthesisString_678 {
	
	/** DP Solution。 N^3
	 * dp[i][j] 表示 s[i, j]之间的substring 是一个 valid。所以他为 ture 有几个条件：
	 * 1, s[i] = '*' && dp[i+1, j] = true
	 * 2, s[i] = '(' && 在 s[i, j] 里面存在一个 s[k] 为 ‘)’ 并且 s[i+1, k] 和 s[k+1, j] 为真 
	 * 我们为什么要从一段字符串的左边开始看呢？
	 * 因为放无论是'(', '*', ')' 放在中间右边都可以成立，唯独放在最左边的时候 ')' 不成立！所以我们需要
	 * 从字符串的左边入手！ */
	
	public boolean checkValidString(String s) {
		int N = s.length();
		if (N == 0) 	return true;
		boolean[][] dp = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			if (s.charAt(i) == '*')
				dp[i][i] = true;
			if (i < N - 1 && (s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(i + 1) == ')' || s.charAt(i + 1) == '*'))
				dp[i][i + 1] = true;
		}

		for (int size = 2; size < N; size++) {
			for (int i = 0; i + size < N; i++) {
				if (s.charAt(i) == '*' && dp[i + 1][i + size] == true) {	// 第一种情况
					dp[i][i + size] = true;
				} else if (s.charAt(i) == '(' || s.charAt(i) == '*') {	// 第二种情况
					for (int k = i + 1; k <= i + size; k++) {
						if ((s.charAt(k) == ')' || s.charAt(k) == '*') && (k == i + 1 || dp[i + 1][k - 1]) && (k == i + size || dp[k + 1][i + size]))
							dp[i][i + size] = true;
					}
				}
			}
		}
		return dp[0][N - 1];
	}
	
	/** Greedy: N */
	
	public boolean checkValidString2(String s) {
		int lo = 0, hi = 0;
		for (char c : s.toCharArray()) {
			lo += (c == '(') ? 1 : -1;
			hi += (c != ')') ? 1 : -1;
			if (hi < 0)
				break;
			lo = Math.max(lo, 0);
		}
		return lo == 0;
	}
	
	/** Backtracking */
	
	public boolean checkValidString3(String s) {
		return check(s, 0, 0);
	}

	private boolean check(String s, int start, int count) {
		if (count < 0)
			return false;

		for (int i = start; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				count++;
			} else if (c == ')') {
				if (count <= 0)
					return false;
				count--;
			} else if (c == '*') {
				return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
			}
		}

		return count == 0;
	}
	
	public static void main(String[] args) {
		String tmp = "(*))";
		
		ValidParenthesisString_678 v = new ValidParenthesisString_678();
		
		System.out.println(v.checkValidString(tmp));
	}
}
