package hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Feb 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LongestConsecutiveSequence_128 {
	
	/** idea 是找每一个sequence 的开头元素，然后向一边搜索。
	 * 找到 sequence 的尽头，然后再更新最大值 */
	
	public int longestConsecutive(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int n : nums)
			set.add(n);
		
		int best = 0;
		for (int n : set) {
			if (!set.contains(n - 1)) { 	// only check for one direction
				int m = n + 1;
				while (set.contains(m)) 	m++;
				
				best = Math.max(best, m - n);
			}
		}
		
		return best;
	}
	
	/** Most voted and talented solution */
	
	public int longestConsecutive1(int[] num) {
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<>(); // 追踪sequence length，存于sequence两端元素
		
		for (int n : num) {
			if (!map.containsKey(n)) {
				int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
				int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
				
				int sum = left + right + 1;		// sum: length of the sequence n is in
				map.put(n, sum);
				
				res = Math.max(res, sum);	// keep track of the max length

				// extend the length to the boundary(s) of the sequence, will do nothing if n has no neighbors
				map.put(n - left, sum);		// 更新 sequence 边界元素的长度
				map.put(n + right, sum);
			}
		}
		
		return res;
	}
}
