package backtracking;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class PalindromePartitioning_131 {
	
	/** Backtracking Solution */
	
	private List<List<String>> res = new ArrayList<List<String>>();
	private ArrayList<String> tmp = new ArrayList<String>();

	public List<List<String>> partition(String s) {
		backTrack(s, 0);
		return res;
	}
	
	// 回溯法来解决当前问题，
	private void backTrack(String s, int start) {
		if (tmp.size() > 0 && start >= s.length()) {		// 当前解集里面有 Palindrome，并且递归已经到底层（遍历完）
			List<String> t =  (List) tmp.clone();
			res.add(t);
		}
		
		for (int ptr = start; ptr < s.length(); ptr++) {
			if (isPalindrome(s, start, ptr)) {		// 检查当前字符串长度给的
				if (start == ptr) 	tmp.add(Character.toString(s.charAt(ptr)));	// 单个字符
				else	 				tmp.add(s.substring(start, ptr + 1));			// 字符串
				
				backTrack(s, ptr + 1);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String str, int l, int r) {
		if (l == r) 	
			return true;
		
		while (l < r) {
			if (str.charAt(l) != str.charAt(r))
				return false;
			l++; r--;
		}
		
		return true;
	}
	
	/** DP Solution */
	
	public static List<List<String>> partition2(String s) {
		int N = s.length();
		List<List<String>>[] result = new List[N + 1];
		result[0] = new ArrayList<List<String>>();
		result[0].add(new ArrayList<String>());

		boolean[][] pair = new boolean[N][N];
		
		for (int i = 0; i < s.length(); i++) {
			result[i + 1] = new ArrayList<List<String>>();
			for (int left = 0; left <= i; left++) {
				
				if (s.charAt(left) == s.charAt(i) && (i - left <= 1 || pair[left + 1][i - 1])) {
					pair[left][i] = true;
					String str = s.substring(left, i + 1);
					for (List<String> r : result[left]) {
						List<String> ri = new ArrayList<String>(r);
						ri.add(str);
						result[i + 1].add(ri);
					}
				}
			}
		}
		return result[N];
	}
}
