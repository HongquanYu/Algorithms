import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Jan 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SlidingWindowTemplate {
	/* Sliding Window 通常用来解决substring problem,给你一个string，让你找到满足一定条件的substring.
	 * 通常的方法是用hashmap加上two pointers */
	
	/* leetcode 438 */
	
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> list = new ArrayList<>();
		
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return list;
		
		int[] occurrence = new int[26];
		for (char c : p.toCharArray())	// occurrence 保存pattern里面字符的个数，供匹配
			occurrence[c - 'a']++;
		
		int l = 0, r = 0;					// window的左右boundary
		int toMatch = p.length();			// 还需要匹配多少个字符才能得到一个解
		
		while (r < s.length()) {	
			// 重点1: 需要从右边加入元素以供搜索解
			if (occurrence[s.charAt(r++) - 'a']-- > 0)	// 右边界进入一个字符可以匹配一个当前pattern里面的字符
				toMatch--;

			if (toMatch == 0)			// 匹配到了所有的字符，目前window里面是一个解，那么加入起始地址
				list.add(l);
			
			// 重点2: 需要从左边移走元素以供求下一个解
			if (r - l == p.length()) 	// window向前移，移走的元素得加上来，
				if (occurrence[s.charAt(l++) - 'a']++ >= 0)		// 移出去的字符在p里，所以我们需要匹配这个字符，故toMatch增1
					toMatch++;
		}
		
		return list;
	}
	
	/* leetcode 76 */
	
	public static String minWindow(String s, String t) {
		
		int occurrence[] = new int[128];
		for (char c : t.toCharArray())
			occurrence[c]++;
		
		int toMatch = t.length();				// 需要匹配的字符个数
		int min = Integer.MAX_VALUE;				// 最短满足条件的字串长度
		int from = 0;							// 最优解开始的index
		
		for (int l = 0, r = 0; r < s.length(); ++r) {
			/* 右边的字符依次进入window， 对每个字符进行自减：
			 * 进入 一个字符 到window：
			 * 	1, occurrence[i] > 0. 表明是t中存在的字符，并且还需要被匹配，故自减
			 * 	2, occurrence[i] == 0. t中没有的，自减后为负 */
			if (occurrence[s.charAt(r)]-- > 0)		 
				toMatch--;						 

			while (toMatch == 0) {		// 匹配成功！这里要做的是找到这里面最优解，并且从左边移除字符直到不是解以便继续处理后续字符
				if (r - l + 1 < min)	{		// 更新最优解
					min = r - l + 1;
					from = l;
				}
				/* 从左边开始移除，并且每个移除的字符要自增 */
				if (occurrence[s.charAt(l++)]++ == 0)		// occurrence[i] == 0 表明这个字符在目前window完全匹配，需要移除，并且reset 计数器toMatch
					toMatch++;
			}
		}

		return min == Integer.MAX_VALUE ? "" : s.substring(from, from + min);
	}
	
	/* leetcode 3 */
	
	public int lengthOfLongestSubstring(String s) {
		
		Set<Character> set = new HashSet<>();
		int n = s.length();
		int len = 0, l = 0, r = 0;
		
		while (l < n && r < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(r))) {	// 当前window里没有这个元素
				set.add(s.charAt(r++));
				len = Math.max(len, r - l);
			} else {								// 当前window有这个元素，直到移除
				set.remove(s.charAt(l++));
			}
		}
		
		return len;
	}
	
	/* leetcode 340 */
	
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		
		int[] chars = new int[256];					// 
		int num = 0, l = 0, len = 0;
		
		for (int r = 0; r < s.length(); r++) {
			if (chars[s.charAt(r)]++ == 0)			// 右边进入的是一个新元素， chars[i] == 0表示之前没出现过
				num++;
			if (num > k) {							// 现在已经超过了允许的k个元素了，要开始从左边减去
				while (--chars[s.charAt(l++)] > 0) ;
				num--;
			}
			
			len = Math.max(len, r - l + 1);				// 更新最大长度
		}
		
		return len;
	}
	
	/* 30 */
	
	public static List<Integer> findSubstring(String s, String[] words) {
		Map<String, Integer> map = new HashMap<>();
		int numWord = words.length;
		int lenWord = words[0].length();
		List<Integer> res = new ArrayList<>();
		
		if (s == null || s.length() < numWord * lenWord)
			return res;

		for (int i = 0; i < words.length; ++i)		// 入hash表每个word的hash 和 word 
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);

		for (int i = 0; i <= s.length() - numWord * lenWord; ++i) {
			if (containsAllWords(s.substring(i, i + numWord * lenWord), lenWord, numWord, map))
				res.add(i);
		}

		return res;
	}
	private static boolean containsAllWords(String s, int lenWord, int numWord, Map<String, Integer> map) {
		Map<String, Integer> tmp = new HashMap<>(map);
		int i = numWord, j = 0;
		
		while (i-- > 0) {
			String t = s.substring(j, j + lenWord);
			if (!tmp.containsKey(t) || tmp.get(t) <= 0)
				return false;
			tmp.put(t, tmp.get(t) - 1);
			j += lenWord;
		}

		return true;
	}
	
	
	
	
	
	/* Summarization */
	
	public List<Integer> slidingWindow(String s, String t) {
		List<Integer> result = new LinkedList<>();
		if (t.length() > s.length())
			return result;

		// create a hashmap to save the Characters of the target substring. - (K, V) = (Character, Frequence of the Characters)
		Map<Character, Integer> map = new HashMap<>();
		for (char c : t.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		// maintain a counter to check whether match the target string.
		int counter = map.size();		// must be the map size, NOT the string size because the char may be duplicate.

		int begin = 0, end = 0;			// Two Pointers: begin - left pointer of the window; end - right pointer of the window

		int len = Integer.MAX_VALUE;		// the length of the substring which match the target string.

		// loop at the begining of the source string
		while (end < s.length()) {

			char c = s.charAt(end);

			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);	// plus or minus one
				if (map.get(c) == 0)
					counter--;				// modify the counter according the requirement(different condition).
			}
			end++;

			// increase begin pointer to make it invalid/valid again
			while (counter == 0 /*
								 * counter condition. different question may have different
								 * condition
								 */) {

				char tempc = s.charAt(begin);	// be careful here: choose the char at begin pointer, NOT the end pointer
				
				if (map.containsKey(tempc)) {
					map.put(tempc, map.get(tempc) + 1);	// plus or minus one
					if (map.get(tempc) > 0)
						counter++;// modify the counter according the requirement(different condition).
				}
				
				begin++;
			}
		}
		return result;
	}
}
