package dp;

/** The tag for this question is DP. So let's try the solution with DP thinking!
 *  Let's assume DP(N) represents the number of palindromes so far until nth character
 *  So we can get:
 *  DP(N) = DP(N - 1) + palindromes(n)
 * 
 * @author Hongquan Yu */

public class PalindromicSubstrings_647 {
	private static String global;
    public static int countSubstrings(String s) {
    		global = s;
    		if (s == null || s.length() == 0)
    			return 0;
    		
    		int[] dp = new int[s.length()];
    		dp[0] = 1;
        
    		for (int i = 1; i < s.length(); ++i) {
    			System.out.println(" Substring: " + s.substring(0, i + 1));
    			dp[i] = dp[i - 1] + 1 + palindromes(s.substring(0, i + 1));
    		}
    	
    		return dp[s.length() - 1];
    }
    
    private static int palindromes(String s) {
    		// count the palindromes start from here, left and right
    		int count_1 = 0;
    		int idx = s.length() - 1;
    		int succ = idx + 1;
    		int pred = idx - 1;
    		while (succ < global.length() && pred >= 0 && global.charAt(succ) == global.charAt(pred) && global.charAt(succ) != global.charAt(idx)) {
    			count_1++;
    			succ++;
    			pred--;
    		}
    		
    		// count the palindromes start from here, to left
    		int count_2 = 0;
    		int pre = idx - 1;
    		while (pre >= 0 && s.charAt(pre) == s.charAt(idx)) {
    			count_2++;
    			pre--;
    		}
    		
    		return count_2 + count_1;
    }
    
    public static void main(String[] args) {
    		String s1 = "aaa";
    		System.out.println(" Substring palindromes are: " + countSubstrings(s1));
    }
}
