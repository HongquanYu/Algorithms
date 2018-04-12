package backtracking;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LetterCombinationsOfAPhoneNumber_17 {
	
	/* Solution with FIFO queue */
	
	public List<String> letterCombinations(String digits) {
		LinkedList<String> ans = new LinkedList<>();
		if (digits == null || digits.length() == 0)
			return ans;
		
		String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while (ans.peek().length() == i) {		// 移除上一个数字添加的字符串并把所有的字符串都加上现如今的字符
				String t = ans.remove();
				for (char s : mapping[x].toCharArray())	// 当前数字代表的字符
					ans.add(t + s);
			}
		}
		
		return ans;
	}
}
