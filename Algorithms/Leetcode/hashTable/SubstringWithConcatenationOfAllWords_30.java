package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubstringWithConcatenationOfAllWords_30 {
	
	/* 149 ms solution */
	
	public static List<Integer> findSubstring(String s, String[] words) {
		Map<String, Integer> map = new HashMap<>();
		List<Integer> res = new ArrayList<>();
		
		int numWord = words.length;
		int lenWord = words[0].length();
		
		if (s == null || s.length() < numWord * lenWord)
			return res;

		for (int i = 0; i < words.length; ++i)		// 入hash表每个word 和其 出现次数 
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);

		for (int i = 0; i <= s.length() - numWord * lenWord; ++i) {
			if (containsAllWords(s.substring(i, i + numWord * lenWord), lenWord, numWord, map))
				res.add(i);
		}

		return res;
	}
	
	// 对当前 map 查找是否每一个单词都存在，并且次数相同！ Java pass by value instead of by reference!
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
	
	public static void main(String[] args) {
//		String s = "barfoofoobarthefoobarman";
//		String[] words = new String[] {"bar","foo","the"};
		
		String s1 = "wordgoodgoodgoodbestword";
		String[] words1 = new String[] {"word","good","best","good"};
		
//		System.out.println(findSubstring(s, words));
		System.out.println(findSubstring(s1, words1));
	}
	
	/* 32ms sliding window solution */
	
	public List<Integer> findSubstring1(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		if (s == null || s.length() == 0 || words == null || words.length == 0)
			return result;

		HashMap<String, Integer> map = new HashMap<>();	// frequency of words
		for (String w : words)
			map.put(w, map.getOrDefault(w, 0) + 1);
		
		int len = words[0].length();

		for (int j = 0; j < len; j++) {
			Map<String, Integer> currentMap = new HashMap<>();
			int start = j;				// start index of start
			int count = 0;				// count total qualified words so far

			for (int i = j; i <= s.length() - len; i += len) {
				String word = s.substring(i, i + len);
				if (map.containsKey(word)) {
					currentMap.put(word, map.getOrDefault(word, 0) + 1);	// set frequency in current map

					count++;
					
					// 当前的字符串里的某个word多于words数组里，remove words from left side of sliding window
					while (currentMap.get(word) > map.get(word)) {
						String left = s.substring(start, start + len);
						currentMap.put(left, currentMap.get(left) - 1);

						count--;
						start = start + len;
					}
					
					// When there is a valid answer
					if (count == words.length) {
						result.add(start); 

						// shift right and reset currentMap, count & start point， 从左边移除一个数
						String left = s.substring(start, start + len);
						currentMap.put(left, currentMap.get(left) - 1);
						count--;
						start = start + len;
					}
				} else {				// 当当前字符串里出现了一个words里没有的word，reset所有的计数器
					currentMap.clear();
					start = i + len;
					count = 0;
				}
			}
		}

		return result;
	}
	
	/* 34ms solution */
	
	public List<Integer> findSubstring3(String s, String[] words) {
		if (words.length == 0 || words[0].length() == 0)
			return new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (String word : words)
			map.put(word, map.getOrDefault(word, 0) + 1);
		List<Integer> list = new ArrayList<>();
		int gap = words[0].length();
		int nlen = words.length * gap;

		for (int k = 0; k < gap; k++) {
			HashMap<String, Integer> wordmap = new HashMap<>(map);
			for (int i = k, j = 0; i < s.length() - nlen + 1 && i + j <= s.length() - gap;) {
				// j is the substring length of have ready matched
				String temp = s.substring(i + j, i + j + gap);
				if (wordmap.containsKey(temp)) {
					wordmap.put(temp, wordmap.get(temp) - 1);
					if (wordmap.get(temp) == 0)
						wordmap.remove(temp);
					if (wordmap.isEmpty())
						list.add(i);
					j += gap;
				} else {

					if (j == 0)
						i += gap;
					else {
						wordmap.put(s.substring(i, i + gap),
								wordmap.getOrDefault(s.substring(i, i + gap), 0) + 1);
						i += gap;
						j -= gap;
					}
				}
			}
		}
		return list;
	}
}
