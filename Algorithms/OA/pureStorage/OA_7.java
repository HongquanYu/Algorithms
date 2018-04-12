package pureStorage;

import java.awt.color.ICC_ColorSpace;

/** @author: Hongquan Yu
 *  @date: Jan 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class OA_7 {
	
	/* 给你一串字符，计算里面的palindrome的个数 */
	
	public int countPalindrom(String s) {
		int cnt = 0;
		
		for (int i = 0; i < s.length(); ++i) {
			for (int j = i + 1; j <= s.length(); ++j) {
				String sub = s.substring(i, j);
				if (isPalindrom(sub))	cnt++;
			}
		}
		
		return cnt;
	}
	
	private boolean isPalindrom(String s) {
		int i = 0, j = s.length() - 1;
		
		while (i < j) {
			if (s.charAt(i) != s.charAt(j))
				return false;
			else {
				j++; j--;
			}
		}
		return true;
	}
	
	public int countPalindroms(String S) {
		if (S == null || S.isEmpty()) {
			return 0;
		}
		char[] array = S.toCharArray();
		int globalCount = array.length;

		for (int mid = 1; mid < array.length; mid++) {
			int count = 0;
			int low = mid - 1, high = mid + 1;
			while (low >= 0 && high < array.length && array[low] == array[high]) {	// 以mid为中心开始向两端找
				count++;
				low--;
				high++;
			}

			globalCount += count;
			count = 0;
			low = mid - 1;
			high = mid;
			while (low >= 0 && high < array.length && array[low] == array[high]) {	// 以mid和mid - 1为中心，向两端找
				count++;
				low--;
				high++;
			}

			globalCount += count;
		}
		
		return globalCount;
	}
}
