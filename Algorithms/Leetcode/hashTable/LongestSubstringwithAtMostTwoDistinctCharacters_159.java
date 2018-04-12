package hashTable;

import java.util.HashMap;

public class LongestSubstringwithAtMostTwoDistinctCharacters_159 {
	
	/* The main idea is to maintain a sliding window with 2 unique characters. The key is to store
	 * the last occurrence of each character as the value in the hashmap. this way, whenever the
	 * size of the hashmap exceeds 2, we can traverse through the map to find the character with the
	 * left most index, and remove 1 character from our map. Since the range of characters is
	 * constrained, we should be able to find the left most index in constant time. */
	
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int n = s.length();
		
		if (n <= 2) 	return 0;
		
		HashMap<Character, Integer> index = new HashMap<>();		// 字符 - 它在window最后出现的索引
		int l = 0, r = 0, len = 0;
		
		while (r < n) {
			if (index.size() <= 2) {			// window里不超过2个元素
				index.put(s.charAt(r), r);		// 保存字符最后出现的位置
				r++;
			} else {							// window里超过了2个元素， 移除最左边那个
				int leftMost = n;
				for (int i : index.values())
					leftMost = Math.min(leftMost, i);
				
				index.remove(s.charAt(leftMost));
				l = leftMost + 1;
			}
			
			len = Math.max(len, r - l);
		}
		
		return len;
	}
	
	public int lengthOfLongestSubstringTwoDistinct2(String s) {

		HashMap<Character, Integer> map = new HashMap<>();
		int start = 0, len = 0, n = s.length();

		for (int i = 0; i < n; i++) {
			
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

			if (map.size() > 2) {
				len = Math.max(len, i - start);

				while (map.size() > 2) {
					char t = s.charAt(start);
					int count = map.get(t);
					if (count > 1) 	map.put(t, count - 1);		// 说明后面还有这个字符，只能删除一个
					else				map.remove(t);				// 后面没有了，直接删掉此记录
					
					start++;
				}
			}
		}

		len = Math.max(len, n - start);

		return len;
	}
}
