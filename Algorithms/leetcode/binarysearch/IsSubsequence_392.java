package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class IsSubsequence_392 {
    public boolean isSubsequence(String s, String t) {
		List<Integer>[] idx = new List[26]; // Just for clarity
		
		for (int i = 0; i < t.length(); i++) {		// cache index of characters from s in t
			if (idx[t.charAt(i) - 'a'] == null)
				idx[t.charAt(i) - 'a'] = new ArrayList<>();
			idx[t.charAt(i) - 'a'].add(i);				// every list in idx is a list of index
		}
		
		int prev = 0;	// 
		
		for (int i = 0; i < s.length(); i++) {			// for every char in s, do search it's
			if (idx[s.charAt(i) - 'a'] == null)		return false; 			// Note: char of S does NOT exist in T causing NPE
			
			int j = Collections.binarySearch(idx[s.charAt(i) - 'a'], prev);	// binary search an index of current char in T
			if (j < 0)		j = -j - 1;										// not found, get the insertion point
			if (j == idx[s.charAt(i) - 'a'].size())		return false;		// current character of s
			prev = idx[s.charAt(i) - 'a'].get(j) + 1;						// index of next char in s
		}
		
		return true;
	}
}
