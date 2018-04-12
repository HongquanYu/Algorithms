package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LongestPalindromicSubstring_5 {
	
	private int lo, maxLen;

	public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2) return s;

		for (int i = 0; i < len - 1; i++) {
			extendPalindrome(s, i, i); 		// Check Odd
			extendPalindrome(s, i, i + 1); 	// Check Even
		}
		return s.substring(lo, lo + maxLen);
	}

	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {	// 找到一个更大的串，更新起始点和长度
			lo = j + 1;
			maxLen = k - j - 1;		// 因为k - 1 - (j + 1) + 1 才是其长度
		}
	}
}
