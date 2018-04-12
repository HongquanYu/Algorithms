package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset_368 {
	
	/* DP[n] - n elements in the subset that can be divisible by each other
	 * DP[n] = DP[n - 1] + x, x is a element that is divisible by every element in the subset
	 */
	public static List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();

		if (nums.length == 0)
			return result;

		int[] divCount = new int[nums.length];
		int[] prev = new int[nums.length];
		int maxIndex = 0;

		Arrays.sort(nums);
		Arrays.fill(divCount, 1);
		Arrays.fill(prev, -1);

		for (int i = 1; i < nums.length; ++i) {
			for (int j = i - 1; j >= 0; --j) {
				if (nums[i] % nums[j] == 0 && divCount[j] + 1 > divCount[i]) {	
					divCount[i] = divCount[j] + 1;
					prev[i] = j;
				}
			}

			if (divCount[maxIndex] < divCount[i])	// track max divisible
				maxIndex = i;
		}

		while (maxIndex != -1) {
			result.add(nums[maxIndex]);
			maxIndex = prev[maxIndex];
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] a = {4, 8, 10, 240};
		
		System.out.println(largestDivisibleSubset(a));
	}
}
