package twoPointers;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {
	public int lengthOfLongestSubstring(String s) {
		
		Set<Character> set = new HashSet<>();
		int n = s.length();
		int len = 0, l = 0, r = 0;
		
		while (l < n && r < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(r))) {	// 当前window里没有这个元素
				set.add(s.charAt(r++));
				len = Math.max(len, r - l);
			} else {								// 当前window有这个元素，直到移除
				set.remove(s.charAt(l++));
			}
		}
		
		return len;
	}
	
 	public int lengthOfLongestSubstring1(String s) {
		
		int[] substring = new int[256];
		int len = 0, l = 0, r = 0;
		
		while (r < s.length()) {
			// try to extend the range [i, j]
			if (substring[s.charAt(r)] == 0) {	// 当前window里没有这个元素
				substring[s.charAt(r++)]++;
				len = Math.max(len, r - l);
			} else {								// 当前window有这个元素，直到移除
				substring[s.charAt(l++)]--;
			}
		}
		
		return len;
	}
}
