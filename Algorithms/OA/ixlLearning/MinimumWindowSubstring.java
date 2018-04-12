package ixlLearning;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MinimumWindowSubstring {
	
	/** 滑动窗口来解决当前问题，LeetCode 76 */
	
	public static String minWindow(String s, String t) {
		int times[] = new int[128];
		for (char c : t.toCharArray())
			times[c]++;
		
		int toMatch = t.length();				// 需要匹配的字符个数
		int min = Integer.MAX_VALUE;				// 最短满足条件的字串长度
		int from = 0;							// 最优解开始的index
		
		for (int l = 0, r = 0; r < s.length(); ++r) {
			if (times[s.charAt(r)]-- > 0)		 
				toMatch--;						 

			while (toMatch == 0) {
				if (r - l + 1 < min)	{		// 更新最优解
					min = r - l + 1;
					from = l;
				}
				/* 从左边开始移除，并且每个移除的字符要自增 */
				if (times[s.charAt(l++)]++ == 0)		// times[i] == 0 表明这个字符在目前window完全匹配，需要移除，并且reset 计数器toMatch
					toMatch++;
			}
		}

		return min == Integer.MAX_VALUE ? "" : s.substring(from, from + min);
	}
}
