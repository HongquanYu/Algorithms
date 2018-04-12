package binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class IntersectionOfTwoArraysII_350 {
	
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> temp = new ArrayList<>();
		
		for (int i : nums1)
			map.put(i, map.getOrDefault(i, 0) + 1);

		for (int j : nums2) {
			int t = map.getOrDefault(j, 0);
			if (t > 0) {
				temp.add(j);
				map.put(j, t - 1);
			}
		}
		
		return temp.stream().mapToInt(Integer::intValue).toArray();
	}
}
