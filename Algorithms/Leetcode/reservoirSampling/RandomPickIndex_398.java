package reservoirSampling;

import java.util.Random;

/** @author: Hongquan Yu
 *  @date: Mar 14, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RandomPickIndex_398 {
	int[] nums;
	Random rnd;

	public RandomPickIndex_398(int[] nums) {
		this.nums = nums;
		this.rnd = new Random();
	}

	public int pick(int target) {
		int result = -1;
		int count = 0;
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != target)
				continue;
			if (rnd.nextInt(++count) == 0)
				result = i;
		}

		return result;
	}
}
