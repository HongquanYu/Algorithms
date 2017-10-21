package binarysearch;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TwoSumII_InputArrayIsSorted_167 {
	
	/* Two pointers solution, Optimal solution */
	
	public int[] twoSum(int[] numbers, int target) {
		int low = 0, high = numbers.length - 1;
		
		while (low < high) {
			int sum = numbers[low] + numbers[high];
			if (sum == target)
				return new int[] {low + 1, high + 1};
			else if (sum < target)
				++low;
			else
				--high;
		}
		return new int[] {-1, -1};
	}
	
	/* Binary search */
	public int[] twoSum2(int[] numbers, int target) {
		for (int i = 0; i <= numbers.length - 1; ++i) {
			int b = Arrays.binarySearch(numbers, target - numbers[i]);
			if(b > 0 && b != i) {
				int [] res = new int[] {i + 1, b + 1};
				Arrays.sort(res);
				return res;
			}
		}
		
		return new int[] {-1, -1};
	}
}
