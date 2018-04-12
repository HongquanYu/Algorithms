package noncomparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comparison.InsertionSort;

/** @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: N, Time: N+K, Worst: N^2. K is the number of buckets! */

public class BucketSort {
	
	private final int DEFAULT_BUCKET_SIZE = 5;

	public void sort(int[] nums) {
		sort(nums, DEFAULT_BUCKET_SIZE);
	}

	private void sort(int[] nums, int bktSize) {
		
		// Find minimum and maximum values
		int min = nums[0], max = nums[0];
		for (int num : nums) {
			if (num < min) 			min = num;
			else if (num > max) 		max = num;
		}
		
		// Initialize buckets
		int bktCount = (max - min) / bktSize + 1;
		List<List<Integer>> buckets = new ArrayList<List<Integer>>(bktCount);
		for (int i = 0; i < bktCount; i++)
			buckets.add(new ArrayList<Integer>());

		// Distribute input array values into buckets
		for (int num : nums)
			buckets.get((num - min) / bktSize).add(num);

		// Sort numbers in buckets and place back into input array
		int idx = 0;
		for (List<Integer> bkt : buckets) {
			int[] sub = bkt.stream().mapToInt(Integer::intValue).toArray();
			
			if (!bkt.isEmpty()) {
				InsertionSort.sort(sub);
				for (int num : sub) nums[idx++] = num;
			}
		}
	}

	public static void main(String[] args) {
		BucketSort bs = new BucketSort();
		int[] data = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
		int[] t2 = {55, 53, 10, 22, 34, 31, 10, 25, 42, 43, 61, 64};

		System.out.println("Before: " + Arrays.toString(data));
		bs.sort(data);
		System.out.println("After:  " + Arrays.toString(data));
		
		System.out.println("Before: " + Arrays.toString(t2));
		bs.sort(t2);
		System.out.println("After:  " + Arrays.toString(t2));
	}
}
