package uncategorized;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Apr 5, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class LongestSubstringWithAtLeastKRepeatingCharacters_395 {
	
	/** Recursion 比较好理解 */
	
	public int longestSubstring(String s, int k) {
		int N = s.length();
		if (N == 0 || N < k)	return 0;
		if (k == 1)	return N;
		
		int[] freq = new int[26];
		for (char c : s.toCharArray())
			freq[c - 'a']++;
		char badchar = 0;
		
		for (int i = 0; i < 26; i++) {
			if (freq[i] > 0 && freq[i] < k) {
				badchar = (char) (i + 'a');
				break;
			}
		}
		
		if (badchar == 0) 	return N;
		
		String[] subs = s.split(badchar + "");
		int max = 0;
		for (String sub : subs)
			max = Math.max(max, longestSubstring(sub, k));
		
		return max;
	}
	
	/** Two Pointer
	 * 因为只包含小写字母，所以最多只会包含 26 个字母，我们尝试对包含不同个数字母进行检查
	 * 每执行一遍，使用 two pointer 进行 sliding window 检查，然后得出最大值 */
	
	public int longestSubstring3(String s, int k) {
		int[] freq = new int[26];
		int idx, max = 0, N = s.length();
		int i = 0, j = 0, unique = 0, noLessThanK = 0;

		for (int h = 1; h <= 26; h++, i = j = noLessThanK = unique = 0) {
			Arrays.fill(freq, 0);
			while (j < N) {		// Sliding Window
				if (unique <= h) {
					idx = s.charAt(j++) - 'a';
					if (freq[idx] == 0)
						unique++;
					freq[idx]++;
					if (freq[idx] == k)
						noLessThanK++;
				} else {
					idx = s.charAt(i++) - 'a';
					if (freq[idx] == k)
						noLessThanK--;
					freq[idx]--;
					if (freq[idx] == 0)
						unique--;
				}
				if (unique == h && unique == noLessThanK)	// 当前有 K 个
					max = Math.max(j - i, max);
			}
		}
		return max;
	}
	
	/** Divide and Conquer */
	
	public int longestSubstring2(String s, int k) {
		return helper(s.toCharArray(), 0, s.length(), k);
	}
	private int helper(char[] str, int start, int end, int k) {
		if (end - start < k)		// substring length shorter than k.
			return 0;
		int[] freq = new int[26];
		for (int i = start; i < end; i++)
			freq[str[i] - 'a']++;
		
		for (int i = 0; i < 26; i++) {
			if (freq[i] < k && freq[i] > 0) { // freq[i]=0 => i+'a' does not exist in the string, skip it.
				for (int j = start; j < end; j++) {
					if (str[j] == i + 'a') {
						int left = helper(str, start, j, k);
						int right = helper(str, j + 1, end, k);
						return Math.max(left, right);
					}
				}
			}
		}
		return end - start;
	}
}
