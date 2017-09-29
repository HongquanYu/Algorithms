package dp;

public class LongestPalindromicSubstring_5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
        		return "";
        
        int len = s.length();
        String longest = "";
        boolean[][] dp = new boolean[len][len];
        
        for (int i = 0; i < len; ++i) {
        		for (int j = 0; j <= i; ++j) {
        			dp[j][i] = (s.charAt(i) == s.charAt(j)) && (i - j <= 2 || dp[j + 1][i - 1]);
        			if (dp[j][i] && (i - j + 1) > longest.length())
        				longest = s.substring(j, i + 1);
        		}
        }
        
        return longest;
    }
    
    // Other solution.
    
	private int lo, maxLen;

	// lo is basically left which is assumed to be the center

	public String longestPalindrome1(String s) {
		int len = s.length();

		if (len < 2)
			return s;

		for (int i = 0; i < len - 1; i++) {	// use two cases to handle even/odd situation.
			extendPalindrome(s, i, i);
			extendPalindrome(s, i, i + 1);
		}

		return s.substring(lo, lo + maxLen);
	}
	
	// to find the longest palindrome range so can be found in the string
	
	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1; // since j -- reduces it by 1
			maxLen = k - j - 1; // k - j - 1 : since k is increased by 1
		}
	}
}
