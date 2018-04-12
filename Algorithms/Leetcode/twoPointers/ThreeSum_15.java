package twoPointers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ThreeSum_15 {
	public List<List<Integer>> threeSum(int[] num) {
		Arrays.sort(num);	// 先排序，以保证结果有序
		List<List<Integer>> res = new LinkedList<>();
		
		for (int i = 0; i < num.length - 2; i++) {
			
			// 遍历数组，对未遍历的后面的元素进行搜索，用two pointers向中间搜索
			if (i == 0 || (i > 0 && num[i] != num[i - 1])) {		// 这里跳过duplicates
				int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
				
				while (lo < hi) {
					if (num[lo] + num[hi] == sum) {		// 找到一对好基友，同时移动两个指针
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
						
						while (lo < hi && num[lo] == num[lo + 1]) lo++;	// skip duplicates
						while (lo < hi && num[hi] == num[hi - 1]) hi--;	// skip duplicates
						lo++;
						hi--;
					} else if (num[lo] + num[hi] < sum) 	lo++;		// 移动小指针以期待更大的和
					else 								hi--;		// 移动大指针以期待更小的和
				}
			}
		}
		
		return res;
	}
}
