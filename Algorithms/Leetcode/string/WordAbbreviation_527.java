package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class WordAbbreviation_527 {
	
	/** 大意就是：
	 * 先对所有的字符串进行缩减并保存，然后遍历结果数组，对每一个缩减的元素后面搜索有没有相同的
	 * 缩减字符串，存在的话就添加进set，然后对 set 里面的重复的缩减们进行原字符串的再缩减。这里
	 * 使用一个 prefix 数组来进行追踪当前字符串的前缀长度！
	 *  */
	
	public List<String> wordsAbbreviation(List<String> dict) {
		int N = dict.size();
		String[] ans = new String[N];
		int[] prefix = new int[N];
		
		for (int i = 0; i < N; i++) {
			prefix[i] = 1;
			ans[i] = buildAbbr(dict.get(i), 1); 	// make abbreviation for each string
		}
		for (int i = 0; i < N; i++) {
			while (true) {
				HashSet<Integer> set = new HashSet<>();
				for (int j = i + 1; j < N; j++)
					if (ans[j].equals(ans[i]))
						set.add(j); 		// check all strings with the same abbreviation
				
				if (set.isEmpty())		// 
					break;
				set.add(i);
				for (int k : set)
					ans[k] = buildAbbr(dict.get(k), ++prefix[k]); // increase the prefix
			}
		}
		return Arrays.asList(ans);
	}
	
	/** 对每一个 string 进行缩减 */

	private String buildAbbr(String s, int k) {
		if (k >= s.length() - 2)
			return s;
		StringBuilder sb = new StringBuilder();
		sb.append(s.substring(0, k));
		sb.append(s.length() - 1 - k);
		sb.append(s.charAt(s.length() - 1));
		
		return sb.toString();
	}
}
