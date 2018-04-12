package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/* The logic is very similar to NO.347 and here we just use a map a count and according to the
 * frequency to put it into the right bucket. Then we go through the bucket to get the most
 * frequently character and append that to the final stringbuilder. */

public class SortCharactersByFrequency_451 {
	
	public String frequencySort(String s) {
		Map<Character, Integer> map = new HashMap<>();
		
		for (char c : s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);
		
		List<Character>[] bucket = new List[s.length() + 1];
		
		for (char key : map.keySet()) {
			int freq = map.get(key);
			
			if (bucket[freq] == null)
				bucket[freq] = new ArrayList<>();
			
			bucket[freq].add(key);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int pos = bucket.length - 1; pos >= 0; pos--) {
			if (bucket[pos] != null) {
				for (char num : bucket[pos]) {
					for (int i = 0; i < map.get(num); i++) {
						sb.append(num);
					}
				}
			}
		}
		
		return sb.toString();
	}
	
	/** 使用优先队列！ */

	public String frequencySort1(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);
		
		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		pq.addAll(map.entrySet());
		StringBuilder sb = new StringBuilder();
		
		while (!pq.isEmpty()) {
			Map.Entry e = pq.poll();
			for (int i = 0; i < (int) e.getValue(); i++)
				sb.append(e.getKey());
		}
		return sb.toString();
	}
}
