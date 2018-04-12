package dp;

/** The tag for this question is DP. So let's try the solution with DP thinking!
 *  Let's assume DP(N) represents the number of palindromes so far until nth character
 *  So we can get:
 *  DP(N) = DP(N - 1) + palindromes(n)
 * 
 * @author Hongquan Yu */

public class PalindromicSubstrings_647 {
        
    /* DP solution 
     * Let's assume that DP(i, j) represents a palindrome of Si...Sj
     * DP(i, j) = DP((i+1, j-1), and Si == Sj)
     * Then the base case is DP(i, i) is true.
     * DP(i, i+1) = (Si == Si+1)
     * 
     * We increase the dp array in this way:
     * 1, There is only one character between Si and Sj, AND Si == Sj,
     * 		then the substring of s[i, j] is a palindrome.
     * 2, There are many characters between Si and Sj, if Si == Sj AND Substring[i+1, j-1] is a palindrome,
     * 		then the substring of s[i, j] is a palindrome.
     * 3, String[i, j] is not a palindrome in other cases.
     * 
     * WHY NEED TWO DIMENTIONAL ARRAY?
     * 
     * BECAUSE the first dimension represents the left boundary of substring, 2nd dimension is the right boundary */
    
	public static int countSubstrings(String s) {
		int N = s.length();
		int res = 0;
		boolean[][] dp = new boolean[N][N];

		for (int i = N - 1; i >= 0; i--) {
			for (int j = i; j < N; j++) {
				dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);
				if (dp[i][j])
					++res;
			}
		}
		return res;
	}
	
	/**  */

	public static int countSubstrings1(String s) {
		if (s == null || s.length() == 0)
			return 0;
		
		int count = 0;
		for (int i = 0; i < s.length(); i++) { 			// i is the mid point
			count += extendPalindrome(s, i, i); 			// odd length;
			count += extendPalindrome(s, i, i + 1); 		// even length
		}

		return count;
	}
	
	/** 找到左右两个 */
	
	private static int extendPalindrome(String s, int left, int right) {
		int count = 0;
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}
		
		return count;
	}
	
    public static void main(String[] args) {
		String s1 = "aaa";									
		String s2 = "bbccaacacdbdbcbcbbbcbadcbdddbabaddbcadb";
		String s3 = "abcccba";								
		String s4 = "abc";
		
		System.out.println(" S1: " + countSubstrings(s1) + "\t Expected " + countSubstrings1(s1));
		System.out.println(" S2: " + countSubstrings(s2) + "\t Expected " + countSubstrings1(s2));
		System.out.println(" S3: " + countSubstrings(s3) + "\t Expected " + countSubstrings1(s3));
		System.out.println(" S4: " + countSubstrings(s4) + "\t Expected " + countSubstrings1(s4));
    }
}
