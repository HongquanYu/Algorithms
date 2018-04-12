package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Mar 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SplitArrayIntoConsecutiveSubsequences_659 {
	
	/** HashMap 追踪频次可以解决
	 *  - Map freq 追踪元素和其当前的次数
	 *  - need 记录当前形成的连续子序列的可以添加的元素
	 *  
	 * 1，当前元素可以添加到当前序列的后面
	 * 2，当前元素可以开始一个新的序列，这得看后面两个元素是否 available */
	
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();		// 记录 元素 - 频次
		Map<Integer, Integer> need = new HashMap<>();		// 记录连续子序列的后续元素
		
		for (int i : nums)
			freq.put(i, freq.getOrDefault(i, 0) + 1);
		
		for (int i : nums) {
			if (freq.get(i) != 0) {
				if (need.getOrDefault(i, 0) > 0) { 	// 添加此元素到当前序列，并更新下一个位置
					need.put(i, need.get(i) - 1);
					need.put(i + 1, need.getOrDefault(i + 1, 0) + 1);
				} else if (freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0) {	// 组成新序列
					freq.put(i + 1, freq.get(i + 1) - 1);
					freq.put(i + 2, freq.get(i + 2) - 1);
					need.put(i + 3, need.getOrDefault(i + 3, 0) + 1);
				} else		// 此元素是孤立的，直接返回 false
					return false;

				freq.put(i, freq.get(i) - 1);	// 当前元素递减 1
			}
		}
		return true;
	}
	
	/** HashMap with PriorityQueue Solution */
	
	private HashMap<Integer, PriorityQueue<Integer>> dmap;
	public boolean isPossible2(int[] nums) {
		dmap = new HashMap<>();
		for (int num : nums) {
			PriorityQueue<Integer> pq0 = getOrPut(num - 1);
			int len = pq0.isEmpty() ? 0 : pq0.poll();
			PriorityQueue<Integer> pq1 = getOrPut(num);
			pq1.offer(len + 1);
		}
		for (int key : dmap.keySet()) {
			for (int len : dmap.get(key)) {
				if (len < 3)
					return false;
			}
		}
		return true;
	}
	public PriorityQueue<Integer> getOrPut(int num) {
		PriorityQueue<Integer> pq = dmap.get(num);
		if (pq == null) {
			pq = new PriorityQueue<Integer>();
			dmap.put(num, pq);
		}
		return pq;
	}
}
