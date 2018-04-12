package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ShortestPalindrome_214 {
	
	/** 最简单的解法！也是暴力解法 */
	
	public String shortestPalindrome5(String s) {
		int N = s.length();
		if (isPalin(s, 0, N - 1))
			return s;
		String reverse = new StringBuilder(s).reverse().toString();
		
		for (int i = N - 2; i >= 0; --i)
			if (isPalin(s, 0, i))
				return reverse.substring(0, N - i - 1) + s;
		return "";
	}
	
	private boolean isPalin(String s, int start, int end) {
		while (start < end)
			if (s.charAt(start++) != s.charAt(end--))
				return false;
		return true;
	}
	
	/** 和上面的解法是一样的逻辑，但是用的recursion */
	
	public String shortestPalindrome(String s) {
		int j = 0, N = s.length();
		
		for (int i = N - 1; i >= 0; i--)		// 从后向前遍历
			if (s.charAt(i) == s.charAt(j))
				j++;
		
		if (j == N) 	return s;	// 当前字符串是回文串
		
		String prefix = s.substring(0, j);
		String suffix = s.substring(j);
		
		String suffixReversed = new StringBuffer(suffix).reverse().toString();
		
		return suffixReversed + shortestPalindrome(prefix) + suffix;
	}
	
	/** KMP 解法，这个是最快的解法 */
	
	public String shortestPalindrome1(String s) {
		String temp = s + "#" + new StringBuilder(s).reverse().toString();
		
		int[] lps = getLPS(temp);
		String sub = s.substring(lps[lps.length - 1]);
		
		return new StringBuilder(sub).reverse().toString() + s;
	}

	private int[] getLPS(String string) {
		int len = 0, j = 1, M = string.length();	// len - length of prefix, j - idx of lps
		int[] lps = new int[M];
		
		while (len < M && j < M) {
			if (string.charAt(j) == string.charAt(len)) {
				lps[j++] = 1 + len++;
			} else {
				if (len == 0) 	lps[j++] = 0;
				else 			len = lps[len - 1];
			}
		}
		return lps;
	}
	
	public static void main(String[] args) {
		ShortestPalindrome_214 s = new ShortestPalindrome_214();
		
		System.out.println(s.shortestPalindrome1("aacecaaa"));
//		System.out.println(s.shortestPalindrome5("aacecaaa"));
//		System.out.println(s.shortestPalindrome("abcd"));
//		System.out.println(s.shortestPalindrome5("abcd"));
//		System.out.println(s.shortestPalindrome("hjklpoiioplkjhgfd"));
//		System.out.println(s.shortestPalindrome5("hjklpoiioplkjhgfd"));
//		System.out.println(s.shortestPalindrome("adcba"));
//		System.out.println(s.shortestPalindrome5("adcba"));
	}
}
