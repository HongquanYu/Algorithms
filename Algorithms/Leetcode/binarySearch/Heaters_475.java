package binarySearch;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Heaters_475 {
	
	/** 思想是：
	 * 我们找到每一个 house 离最近的 heater 的距离，最后取这些距离中的最大值
	 * 解法，排序heaters，遍历并对每一个house进行二叉搜索找到最近的距离 */
	
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int result = Integer.MIN_VALUE;

		for (int house : houses) {
			int index = Arrays.binarySearch(heaters, house);
			if (index < 0)
				index = -index - 1;
			
			int distToPreviousHouse = Integer.MAX_VALUE;
			int distToNextHouse = Integer.MAX_VALUE;
			if (index - 1 >= 0)
				distToPreviousHouse = house - heaters[index - 1];	
			if (index < heaters.length)
				distToNextHouse = heaters[index] - house;
			
			int closerTo = Math.min(distToPreviousHouse, distToNextHouse);
			result = Math.max(result, closerTo);
		}

		return result;
	}
    
	/** Two pointers 
	 * 对于每个house，找到最近的heater，通过比较current heater和next heater*/
    
	public int findRadius2(int[] houses, int[] heaters) {
		Arrays.sort(houses);
		Arrays.sort(heaters);

		int heaterPtr = 0, res = 0;
		for (int house : houses) {
			while (heaterPtr < heaters.length - 1 && 
					heaters[heaterPtr] + heaters[heaterPtr + 1] <= house * 2) // 找到每个 house 最近的 heater
				heaterPtr++;
			
			res = Math.max(res, Math.abs(heaters[heaterPtr] - house));
		}

		return res;
	}
}
