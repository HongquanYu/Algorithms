package hashTable;

import java.util.HashMap;

public class PalindromePermutation_266 {
	public boolean canPermutePalindrome(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < s.length(); i++)
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		
		int count = 0;
		for (char key : map.keySet())	// 字符出现的次数是奇数的个数
			count += map.get(key) % 2;
		
		return count <= 1;
	}
}
