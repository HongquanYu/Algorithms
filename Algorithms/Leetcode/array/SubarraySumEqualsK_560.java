package array;

import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Mar 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class SubarraySumEqualsK_560 {
	
	/**  */
	
	public int subarraySum(int[] nums, int k) {
		int sum = 0, result = 0;
		Map<Integer, Integer> sumToFreq = new HashMap<>();
		sumToFreq.put(0, 1);

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sumToFreq.containsKey(sum - k))
				result += sumToFreq.get(sum - k);
			
			sumToFreq.put(sum, sumToFreq.getOrDefault(sum, 0) + 1);
		}

		return result;
	}
    
    public static void main(String[] args) {
		int[] tmp = new int[] {1,1,1};
		int[] tmp2 = new int[] {1, 3, 4, 2, 3, 1, 4, 3, 3};
		int[] tmp3 = new int[] {1,2,3};
		SubarraySumEqualsK_560 s = new SubarraySumEqualsK_560();
		
		System.out.println(s.subarraySum(tmp, 2));
		System.out.println(s.subarraySum(tmp2, 6));
		System.out.println(s.subarraySum(tmp3, 3));
	}
}
