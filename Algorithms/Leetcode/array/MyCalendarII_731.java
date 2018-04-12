package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** @author: Hongquan Yu
 *  @date: Mar 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MyCalendarII_731 {
	
	/** Time Complexity: N^2, Space: N
	 * 用两个链表来做，这样的时间复杂度有点高。为什么 booking 查找要是找到了重叠返回 false
	 * 的时候要清空重叠list呢？因为每一次booking 都会遍历所有的区间并新建立一个 overlaps */
	
	private List<int[]> bookings = new ArrayList<>();
	private List<int[]> overlaps = new ArrayList<>();

	public boolean book(int start, int end) {
		
		// 检查有没有重复
		for (int[] bo : bookings) {
			int overlapStart	= Math.max(bo[0], start);
			int overlapEnd 	= Math.min(bo[1], end);
			
			if (overlapStart < overlapEnd) {		// 还真和一个 booking 重合了
				for (int[] ov : overlaps) {
					int os = Math.max(ov[0], overlapStart);
					int oe = Math.min(ov[1], overlapEnd);
					if (os < oe) {	// 还真和一个重叠 重叠了，说明这个是不行的，直接返回false
						overlaps.clear();
						return false;
					}
				}
				overlaps.add(new int[]{ overlapStart, overlapEnd });	// 添加新的 overlaps
			}
		}
		bookings.add(new int[]{ start, end });	// 添加新 booking 到记录
		
		return true;
	}
    
    /** TreeMap Solution */
	
	private TreeMap<Integer, Integer> map;	// boundary - frequency

	public MyCalendarII_731() {
		map = new TreeMap<>();
	}

	public boolean book2(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);
		int count = 0;
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			
			count += entry.getValue();
			if (count > 2) {
				map.put(start, map.get(start) - 1);
				if (map.get(start) == 0)
					map.remove(start);
				
				map.put(end, map.get(end) + 1);
				if (map.get(end) == 0)
					map.remove(end);
				
				return false;
			}
		}
		return true;
	}
}
