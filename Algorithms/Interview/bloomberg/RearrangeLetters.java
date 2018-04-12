package bloomberg;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RearrangeLetters {
	public String rearrabge(String s) {
		if (s == null)
			return null;
		
		StringBuilder res = new StringBuilder();
		Map<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ?
				a.getKey() - b.getKey() : b.getValue() - a.getValue());
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			pq.offer(e);
		}
		while (!pq.isEmpty()) {
			int count = pq.peek().getValue();
			char c = pq.poll().getKey();
			while (count > 0) {
				res.append(c);
				count--;
			}
		}
		
		return res.toString();
	}
	
	public static void main(String[] args) {
		RearrangeLetters r = new RearrangeLetters();
		
		System.out.println(r.rearrabge("hhggbbbbtta"));
		System.out.println(r.rearrabge("a"));
		System.out.println(r.rearrabge(null));
	}
}
