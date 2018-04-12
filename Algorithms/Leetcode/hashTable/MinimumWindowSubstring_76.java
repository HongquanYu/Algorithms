package hashTable;

public class MinimumWindowSubstring_76 {
	
	/** 滑动窗口题
	 * 用一个数组来存字符和他的频度，对于每一个进入窗口的字符都+1，同样出的都-1.然后是当前窗口达到了
	 * 一个有效解的时候一定得从左边出直到出现一个无效解 */
	
	public static String minWindow(String s, String t) {
		int times[] = new int[128];
		for (char c : t.toCharArray())
			times[c]++;
		
		int toMatch = t.length();				// 需要匹配的字符个数
		int min = Integer.MAX_VALUE;				// 最短满足条件的字串长度
		int from = 0;							// 最优解开始的index
		
		for (int l = 0, r = 0; r < s.length(); ++r) {
			/* 右边的字符依次进入window， 对每个字符进行自减：
			 * 进入 一个字符 到window：
			 * 	1, times[i] > 0. 表明是t中存在的字符，并且还需要被匹配，故自减
			 * 	2, times[i] == 0. t中没有的，自减后为负 */
			if (times[s.charAt(r)]-- > 0)		 
				toMatch--;						 

			while (toMatch == 0) {		// 匹配成功！这里要做的是找到这里面最优解，并且从左边移除字符直到不是解以便继续处理后续字符
				if (r - l + 1 < min)	{		// 更新最优解
					min = r - l + 1;
					from = l;
				}
				/* 从左边开始移除，并且每个移除的字符要自增 */
				if (times[s.charAt(l++)]++ == 0)		// times[i] == 0 表明这个字符在目前window完全匹配，需要移除，并且reset 计数器toMatch
					toMatch++;
			}
		}

		return min == Integer.MAX_VALUE ? "" : s.substring(from, from + min);
	}
	
	
	public static void main(String[] args) {
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String t = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		System.out.println(minWindow(s, t));
	}
}
