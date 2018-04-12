package divideAndConquer;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CountOfRangeSum_327 {
	
	/* Merge sort */
	
	public int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length;
		long[] sums = new long[n + 1];
		for (int i = 0; i < n; ++i)
			sums[i + 1] = sums[i] + nums[i];
		return countWhileMergeSort(sums, 0, n + 1, lower, upper);
	}

	private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
		if (end - start <= 1)
			return 0;
		int mid = (start + end) / 2;
		int count = countWhileMergeSort(sums, start, mid, lower, upper)
				+ countWhileMergeSort(sums, mid, end, lower, upper);
		int j = mid, k = mid, t = mid;
		long[] cache = new long[end - start];
		for (int i = start, r = 0; i < mid; ++i, ++r) {
			while (k < end && sums[k] - sums[i] < lower)
				k++;
			while (j < end && sums[j] - sums[i] <= upper)
				j++;
			while (t < end && sums[t] < sums[i])
				cache[r++] = sums[t++];
			cache[r] = sums[i];
			count += j - k;
		}
		System.arraycopy(cache, 0, sums, start, t - start);
		return count;
	}
	
	/* Divide and conquer */
	
	public int countRangeSum2(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0 || lower > upper)
			return 0;
		return countRangeSumSub(nums, 0, nums.length - 1, lower, upper);
	}

	private int countRangeSumSub(int[] nums, int l, int r, int lower, int upper) {
		if (l == r)
			return nums[l] >= lower && nums[r] <= upper ? 1 : 0; // base case

		int m = l + (r - l) / 2;
		long[] arr = new long[r - m]; // prefix array for the second subarray
		long sum = 0;
		int count = 0;

		for (int i = m + 1; i <= r; i++) {
			sum += nums[i];
			arr[i - (m + 1)] = sum; // compute the prefix array
		}

		Arrays.sort(arr); // sort the prefix array

		// Here we can compute the suffix array element by element.
		// For each element in the suffix array, we compute the corresponding
		// "insertion" indices of the modified bounds in the sorted prefix array
		// then the number of valid ranges sums will be given by the indices difference.
		// I modified the bounds to be "double" to avoid duplicate elements.
		sum = 0;
		for (int i = m; i >= l; i--) {
			sum += nums[i];
			count += findIndex(arr, upper - sum + 0.5) - findIndex(arr, lower - sum - 0.5);
		}

		return countRangeSumSub(nums, l, m, lower, upper)
				+ countRangeSumSub(nums, m + 1, r, lower, upper) + count;
	}

	// binary search function
	private int findIndex(long[] arr, double val) {
		int l = 0, r = arr.length - 1, m = 0;

		while (l <= r) {
			m = l + (r - l) / 2;

			if (arr[m] <= val) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}

		return l;
	}
}
