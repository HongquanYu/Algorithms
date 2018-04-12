package binarySearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import array.Interval;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindRightInterval_436 {
	
	/** 简单的高效的 Solution！ 
	 * 因为我们只关注区间结尾和区间开始之间的关系，还有存储区间的索引。所以我们用 Map 存储区间的开始和索引，
	 * 遍历输入数组的时候搜索本区间尾的 ceilingKey 来找到下一个区间！这也是为什么要用有序的 TreeMap 的原因 */
	
	public int[] findRightInterval2(Interval[] intervals) {
		int N = intervals.length;
		int[] res = new int[N];
		TreeMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i < N; ++i)
			map.put(intervals[i].start, i); // Interval.start - Index pair

		for (int i = 0; i < N; ++i) {
			Integer key = map.ceilingKey(intervals[i].end);
			res[i] = key != null ? map.get(key) : -1;
		}

		return res;
	}
	
	/** Worst quadratic! */
	
	public int[] findRightInterval(Interval[] intervals) {
		int N = intervals.length;
		Interval[] endIntervals = Arrays.copyOf(intervals, N);
		Map<Interval, Integer> hash = new HashMap<>();
		
		for (int i = 0; i < N; i++)		// Inter	val - index pair!
			hash.put(intervals[i], i);
		
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		Arrays.sort(endIntervals, (a, b) -> a.end - b.end);
		
		int j = 0;
		int[] res = new int[N];
		
		/* 遍历数组，对每一个元素都找到一个 Interval 他的头元素比他大, 然后将它的index添加到result set！
		   这也就是为什么要一个HashMap来保存原数组的Interval和index的原因 */
		for (int i = 0; i < N; i++) {
			while (j < N && intervals[j].start < endIntervals[i].end)
				j++;
			res[hash.get(endIntervals[i])] = (j == N) ? -1 : hash.get(intervals[j]);
		}
		
		return res;
	}
}
