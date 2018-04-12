package twoPointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Implement_strStr_28 {
	
	/** 双指针能解决这个问题 */
	
	public int strStr(String haystack, String needle) {
		for (int i = 0; ; i++) {
			for (int j = 0; ; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}
	
	/** 同时也可以使用 KMP 解法 */
	
	public int strStr1(String haystack, String needle) {
		int N = haystack.length(), M = needle.length();
		int[] lps = getLPS(needle);
		
		for (int i = 0, j = 0; i < N && j < M; ) {
			char p = needle.charAt(j), h = haystack.charAt(i);
			if (p == h) { i++; j++; }
			if (j == M)	return i - j;
			else if (i < N && p != h) {
				if (j != 0)	j = lps[j - 1];
				else 		i++;
			}
		}
		return -1;
	}
	
	
	private int[] getLPS(String pat) {
		int i = 0, j = 1, N = pat.length();
		int[] lps = new int[N];
		
		while (i < N && j < N) {
			if (pat.charAt(i) == pat.charAt(j)) {
				lps[j++] = 1 + i++;
			} else {
				if (i == 0)	lps[j++] = 0;
				else			i = lps[i - 1];
			}
		}
		return lps;
	}
}
