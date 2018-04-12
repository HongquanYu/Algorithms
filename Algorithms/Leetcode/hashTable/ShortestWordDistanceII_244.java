package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII_244 {
	private Map<String, List<Integer>> map;

	public ShortestWordDistanceII_244(String[] words) {
		
		map = new HashMap<String, List<Integer>>();	// 单词和他的occurrence 列表
		
		for (int i = 0; i < words.length; i++) {
			
			String w = words[i];
			
			if (map.containsKey(w)) {
				map.get(w).add(i);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				map.put(w, list);
			}
		}
	}

	public int shortest(String word1, String word2) {
		
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0, j = 0; i < list1.size() && j < list2.size() ; ) {
			int index1 = list1.get(i), index2 = list2.get(j);
			if (index1 < index2) {
				min = Math.min(min, index2 - index1);
				i++;
			} else {
				min = Math.min(min, index1 - index2);
				j++;
			}
		}
		
		return min;
	}
}
