package dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordBreak2_140 {
	
	/*
	 * Recursion. Time complexity : O(n^n). Space complexity : O(n^3). TLE
	 */
	
	public List<String> wordBreak1(String s, List<String> wordDict) {
		return word_Break1(s, wordDict, 0);
	}

	public List<String> word_Break1(String s, List<String> wordDict, int start) {
		
		LinkedList<String> res = new LinkedList<>();
		
		if (start == s.length()) {
			res.add("");
		}
		
		for (int end = start + 1; end <= s.length(); end++) {
			
			if (wordDict.contains(s.substring(start, end))) {
				
				List<String> list = word_Break1(s, wordDict, end);
				
				for (String l : list) {
					res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
				}
			}
		}
		
		return res;
	}
    
	
	/*
	 *  Recursion with memoization. T O(n^3), S O(n^3). AC
	 */
	
	public List<String> wordBreak2(String s, List<String> wordDict) {
		return word_Break2(s, wordDict, 0);
	}

	HashMap<Integer, List<String>> map = new HashMap<>();

	public List<String> word_Break2(String s, List<String> wordDict, int start) {
		
		if (map.containsKey(start)) {
			return map.get(start);
		}
		
		LinkedList<String> res = new LinkedList<>();
		
		if (start == s.length()) {
			res.add("");
		}
		
		for (int end = start + 1; end <= s.length(); end++) {
			if (wordDict.contains(s.substring(start, end))) {
				List<String> list = word_Break2(s, wordDict, end);
				for (String l : list) {
					res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
				}
			}
		}
		
		map.put(start, res);
		return res;
	}
	
	/**
	 * DP solution. T O(n^3), S O(n^3). TLE
	 */
	
	public List<String> wordBreak(String s, List<String> wordDict) {
		
		LinkedList<String>[] dp = new LinkedList[s.length() + 1];
		LinkedList<String> initial = new LinkedList<>();
		
		initial.add("");
		dp[0] = initial;
		
		for (int i = 1; i <= s.length(); i++) {
			
			LinkedList<String> list = new LinkedList<>();
			
			for (int j = 0; j < i; j++) {
				
				if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
					
					for (String l : dp[j]) {	// add current satisfied word to every item of list
						list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
					}
				}
			}
			
			dp[i] = list;
		}
		
		return dp[s.length()];
	}
	
}
