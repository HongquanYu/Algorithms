package hashtable;

import java.util.HashMap;

public class LongestSubstringwithAtMostTwoDistinctCharacters_159 {
	
	/*
	 * The main idea is to maintain a sliding window with 2 unique characters. The key is to store
	 * the last occurrence of each character as the value in the hashmap. This way, whenever the
	 * size of the hashmap exceeds 2, we can traverse through the map to find the character with the
	 * left most index, and remove 1 character from our map. Since the range of characters is
	 * constrained, we should be able to find the left most index in constant time.
	 */
	
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s.length() < 1)
			return 0;
		HashMap<Character, Integer> index = new HashMap<Character, Integer>();
		int lo = 0;
		int hi = 0;
		int maxLength = 0;
		while (hi < s.length()) {
			if (index.size() <= 2) {
				char c = s.charAt(hi);
				index.put(c, hi);
				hi++;
			}
			if (index.size() > 2) {
				int leftMost = s.length();
				for (int i : index.values()) {
					leftMost = Math.min(leftMost, i);
				}
				char c = s.charAt(leftMost);
				index.remove(c);
				lo = leftMost + 1;
			}
			maxLength = Math.max(maxLength, hi - lo);
		}
		return maxLength;
	}
}
