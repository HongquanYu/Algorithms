package dp;

/** 87. Scramble String
 * 
 *  Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * 
 * To understand this question, we need to aware that one scramble string should be swaped of every
 * non-leaf node of the original string. */

public class ScrambleString_87 {
	
	// recursion
	
    public boolean isScramble(String s1, String s2) {
    	
		if (s1.equals(s2)) return true;
        
		int[] letters = new int[26];
        
		for (int i = 0; i < s1.length(); i++) {	// 
			letters[s1.charAt(i) - 'a']++;
			letters[s2.charAt(i) - 'a']--;
		}
        
		for (int i = 0; i < 26; i++)			// check if the two strings have same frequency of letters
			if (letters[i] != 0)
				return false;
    
		for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) 		// 
            		&& isScramble(s1.substring(i), s2.substring(i))) 	// 
            	return true;
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) 			// 
            		&& isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) 	// 
            	return true;
        }
		
        return false;
    }
    
    // dp solution
    
    public boolean isScramble2(String s1, String s2) {
    	
		if (s1.length() != s2.length())		// false if two strings are not equal length
			return false;
		
		int len = s1.length();
		
		boolean [][][] F = new boolean[len][len][len + 1];
		
		for (int k = 1; k <= len; ++k)
			for (int i = 0; i + k <= len; ++i)
				for (int j = 0; j + k <= len; ++j) {
					if (k == 1) {
						if (s1.charAt(i) == s2.charAt(j))
							F[i][j][k] = true;
					} else
						for (int q = 1; q < k && !F[i][j][k]; ++q) {
							F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
						}
				}
					
		
		return F[0][0][len];
	}
}
