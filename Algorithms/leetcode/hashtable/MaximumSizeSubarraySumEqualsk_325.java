package hashtable;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsk_325 {
	
	/*
	 * The HashMap stores the sum of all elements before index i as key, and i as value. For each i,
	 * check not only the current sum but also (currentSum - previousSum) to see if there is any
	 * that equals k, and update max length.
	 */

	public int maxSubArrayLen(int[] nums, int k) {
		int sum = 0, max = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			if (sum == k)
				max = i + 1;
			else if (map.containsKey(sum - k))
				max = Math.max(max, i - map.get(sum - k));
			if (!map.containsKey(sum))
				map.put(sum, i);
		}
		return max;
	}
	
	/*
	 * The subarray sum reminds me the range sum problem. Preprocess the input array such that you
	 * get the range sum in constant time. sum[i] means the sum from 0 to i inclusively the sum from
	 * i to j is sum[j] - sum[i - 1] except that from 0 to j is sum[j].
	 * 
	 * j-i is equal to the length of subarray of original array. we want to find the max(j - i) for
	 * any sum[j] we need to find if there is a previous sum[i] such that sum[j] - sum[i] = k
	 * Instead of scanning from 0 to j -1 to find such i, we use hashmap to do the job in constant
	 * time. However, there might be duplicate value of of sum[i] we should avoid overriding its
	 * index as we want the max j - i, so we want to keep i as left as possible.
	 */
	public int maxSubArrayLen1(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;
		int n = nums.length;
		for (int i = 1; i < n; i++)
			nums[i] += nums[i - 1];
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1); // add this fake entry to make sum from 0 to j consistent
		int max = 0;
		for (int i = 0; i < n; i++) {
			if (map.containsKey(nums[i] - k))
				max = Math.max(max, i - map.get(nums[i] - k));
			if (!map.containsKey(nums[i])) // keep only 1st duplicate as we want first index as left
											// as possible
				map.put(nums[i], i);
		}
		return max;
	}
}
