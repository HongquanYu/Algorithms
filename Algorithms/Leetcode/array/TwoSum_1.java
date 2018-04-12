package array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {
	public int[] twoSum(int[] nums, int target) {
		
		Map<Integer, Integer> map = new HashMap<>();		// Value - index pair!
		
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			
			if (map.containsKey(complement)) {
				return new int[] {map.get(complement), i};
			}
			
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
}
