package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IsSubsequence_392 {
    public static boolean isSubsequence(String s, String t) {
        int lenT = t.length(), lenS = s.length();
        if (lenS == 0)				// if s is an empty string, return true
        	return true;
        if (lenS > lenT)			// length of s is larger than length of t, then return false;
        	return false;
        
        
        int ps = 0;
        for (int pt = 0; pt < lenT; ++pt) {
        	if (t.charAt(pt) == s.charAt(ps)) {		// current char is meet, move to next one
        		ps++;
        		if (ps == lenS)			// we have traversed the whole string of s, then return true
            		return true;
        		continue;
        	}
        }
    	
    	return false;
    }
    
    /* Follow up: Many queries!
     * Binary Search Solution */
    
	public static boolean isSubsequence_1(String s, String t) {
		List<Integer>[] idx = new List[26]; // Just for clarity
		
		for (int i = 0; i < t.length(); i++) {		// cache index of characters from s in t
			if (idx[t.charAt(i) - 'a'] == null)
				idx[t.charAt(i) - 'a'] = new ArrayList<>();
			idx[t.charAt(i) - 'a'].add(i);				// every list in idx is a list of index
		}
		
		int prev = 0;	// index of previous char, so we have to search valid char after that index
		
		for (int i = 0; i < s.length(); i++) {			// for every char in s, do search it's
			if (idx[s.charAt(i) - 'a'] == null)		return false; 			// Note: char of S does NOT exist in T causing NPE
			
			int j = Collections.binarySearch(idx[s.charAt(i) - 'a'], prev);	// binary search an index of current char in T
			if (j < 0)		j = -j - 1;										// not found, get the insertion point
			if (j == idx[s.charAt(i) - 'a'].size())		return false;		// current character of s
			prev = idx[s.charAt(i) - 'a'].get(j) + 1;						// index of next char in s
		}
		
		return true;
	}
	
    
    public static void main(String[] args) {
    	String S = "abc";
    	String T = "bahbgdca";
    	
    	System.out.println(isSubsequence_1(S, T));
    }
}
