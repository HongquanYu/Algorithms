package ixlLearning;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class AnagramDifference {
	public int[] getMinDifference(String[] A, String[] B) {
		if (A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length)
			return new int[0];
		
		int[] res = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			String s1 = A[i], s2 = B[i];
			
			if (s1.length() != s2.length()) {
				res[i] = -1;
				continue;
			}
			
			int[] freq = new int[26];
			for (int j = 0; j < s1.length(); ++j) {
				freq[s1.charAt(j) - 'a']++;
				freq[s2.charAt(j) - 'a']--;
			}
			for (int k = 0; k < freq.length; ++k)
				res[i] += Math.abs(freq[k]);
			res[i] /= 2;
		}
		return res;
	}
}
