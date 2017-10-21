package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords_30 {
	
	/* Does not come through */
	
	private Map<Integer, String> map = new HashMap<>();
	private int lenWord;
	private int numWord;

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || s.length() < numWord * lenWord)
			return res;
		numWord = words.length;
		lenWord = words[0].length();

		for (int i = 0; i < words.length; ++i)
			map.put(words[i].hashCode(), words[i]);

		for (int i = 0; i < s.length() - numWord * lenWord; ++i) {
			if (containsWords(s.substring(i, i + numWord * lenWord)))
				res.add(i);
		}

		return res;
	}

	private boolean containsWords(String s) {
		int i = numWord, j = 0;
		while (i-- > 0) {
			if (!map.containsKey(s.substring(j, j + lenWord).hashCode()))
				return false;
			j += (lenWord - 1);
		}

		return true;
	}
	
	
	public List<Integer> findSubstring1(String s, String[] words) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return result;
		}

		// frequency of words
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String w : words) {
			if (map.containsKey(w)) {
				map.put(w, map.get(w) + 1);
			} else {
				map.put(w, 1);
			}
		}

		int len = words[0].length();

		for (int j = 0; j < len; j++) {
			HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
			int start = j;				// start index of start
			int count = 0;				// count total qualified words so far

			for (int i = j; i <= s.length() - len; i = i + len) {
				String sub = s.substring(i, i + len);
				if (map.containsKey(sub)) {
					// set frequency in current map
					if (currentMap.containsKey(sub)) {
						currentMap.put(sub, currentMap.get(sub) + 1);
					} else {
						currentMap.put(sub, 1);
					}

					count++;
					
					// remove words from left side of sliding window
					while (currentMap.get(sub) > map.get(sub)) {
						String left = s.substring(start, start + len);
						currentMap.put(left, currentMap.get(left) - 1);

						count--;
						start = start + len;
					}
					
					// When there is a valid answer
					if (count == words.length) {
						result.add(start); 

						// shift right and reset currentMap, count & start point
						String left = s.substring(start, start + len);
						currentMap.put(left, currentMap.get(left) - 1);
						count--;
						start = start + len;
					}
				} else {
					currentMap.clear();
					start = i + len;
					count = 0;
				}
			}
		}

		return result;
	}
}
