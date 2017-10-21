package hashtable;

import java.util.HashSet;

public class LongestPalindrome_409 {
	public int longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashSet<Character> hs = new HashSet<Character>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (hs.contains(s.charAt(i))) {
				hs.remove(s.charAt(i));
				count++;
			} else {
				hs.add(s.charAt(i));
			}
		}
		if (!hs.isEmpty())
			return count * 2 + 1;
		return count * 2;
	}

	public int longestPalindrome1(String s) {
		int[] lowercase = new int[26];
		int[] uppercase = new int[26];
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			char temp = s.charAt(i);
			if (temp >= 97)
				lowercase[temp - 'a']++;
			else
				uppercase[temp - 'A']++;
		}
		for (int i = 0; i < 26; i++) {
			res += (lowercase[i] / 2) * 2;
			res += (uppercase[i] / 2) * 2;
		}
		
		return res == s.length() ? res : res + 1;
	}
}
