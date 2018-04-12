package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs_336 {
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> ret = new ArrayList<>();
		
		if (words == null || words.length < 2)
			return ret;
		
		Map<String, Integer> map = new HashMap<>();		// word - index pair
		
		for (int i = 0; i < words.length; i++)
			map.put(words[i], i);
		
		for (int i = 0; i < words.length; i++) {		// 遍历每个word
			for (int j = 0; j <= words[i].length(); j++) {	// 遍历当前word的所有位, 并把当前单词分成两半
				String s1 = words[i].substring(0, j);
				String s2 = words[i].substring(j);
				
				// 单词前半部分是palindrome，单词的后半部分的reverse要是在集合里出现，我们只需要将它家在最前面就构成了一个palindrome
				if (isPalindrome(s1)) {
					String s2rvs = new StringBuilder(s2).reverse().toString();
					if (map.containsKey(s2rvs) && map.get(s2rvs) != i) {		// 存在一个
						List<Integer> list = new ArrayList<Integer>();
						list.add(map.get(s2rvs));	// 找到的另一个在前面
						list.add(i);					// 单词i在后面
						ret.add(list);
					}
				}
				
				if (isPalindrome(s2)) {
					String s1rvs = new StringBuilder(s1).reverse().toString();
					// check "str.length() != 0" to avoid duplicates
					if (map.containsKey(s1rvs) && map.get(s1rvs) != i && s2.length() != 0) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(i);
						list.add(map.get(s1rvs));
						ret.add(list);
					}
				}
			}
		}
		
		return ret;
	}

	private boolean isPalindrome(String str) {
		int left = 0, right = str.length() - 1;
		
		while (left <= right) {
			if (str.charAt(left++) != str.charAt(right--))
				return false;
		}
		
		return true;
	}
}
