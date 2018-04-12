package array;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Apr 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class FindAllNumbersDisappearedInAnArray_448 {
	
	/** 将出现过的元素设为负，第二遍遍历就能找到了 */
	
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			int idx = Math.abs(nums[i]) - 1;
			if (nums[idx] > 0)
				nums[idx] = -nums[idx];
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				res.add(i + 1);
		}
		return res;
	}
}
