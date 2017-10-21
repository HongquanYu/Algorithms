package dp;

import java.util.TreeSet;

public class MaxSumofRectangleNoLargerThanK_363 {

	/* Merge sort */

	public int maxSumSubmatrix(int[][] matrix, int k) {
		int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
		long[] sum = new long[m + 1]; // stores sum of rect[0..p][i..j]
		for (int i = 0; i < n; ++i) {
			long[] sumInRow = new long[m];
			for (int j = i; j < n; ++j) { // for each rect[*][i..j]
				for (int p = 0; p < m; ++p) {
					sumInRow[p] += matrix[p][j];
					sum[p + 1] = sum[p] + sumInRow[p];
				}
				ans = Math.max(ans, mergeSort(sum, 0, m + 1, k));
				if (ans == k)
					return k;
			}
		}
		return ans;
	}

	int mergeSort(long[] sum, int start, int end, int k) {
		if (end == start + 1)
			return Integer.MIN_VALUE; // need at least 2 to proceed
		int mid = start + (end - start) / 2, cnt = 0;
		int ans = mergeSort(sum, start, mid, k);
		if (ans == k)
			return k;
		ans = Math.max(ans, mergeSort(sum, mid, end, k));
		if (ans == k)
			return k;
		long[] cache = new long[end - start];
		for (int i = start, j = mid, p = mid; i < mid; ++i) {
			while (j < end && sum[j] - sum[i] <= k)
				++j;
			if (j - 1 >= mid) {
				ans = Math.max(ans, (int) (sum[j - 1] - sum[i]));
				if (ans == k)
					return k;
			}
			while (p < end && sum[p] < sum[i])
				cache[cnt++] = sum[p++];
			cache[cnt++] = sum[i];
		}
		System.arraycopy(cache, 0, sum, start, cnt);
		return ans;
	}

	public int maxSumSubmatrix1(int[][] matrix, int k) {
		// 2D Kadane's algorithm + 1D maxSum problem with sum limit k
		// 2D subarray sum solution

		// boundary check
		if (matrix.length == 0)
			return 0;

		int m = matrix.length, n = matrix[0].length;
		int result = Integer.MIN_VALUE;

		// outer loop should use smaller axis
		// now we assume we have more rows than cols, therefore outer loop will be based on cols
		for (int left = 0; left < n; left++) {
			// array that accumulate sums for each row from left to right
			int[] sums = new int[m];
			for (int right = left; right < n; right++) {
				// update sums[] to include values in curr right col
				for (int i = 0; i < m; i++) {
					sums[i] += matrix[i][right];
				}

				// we use TreeSet to help us find the rectangle with maxSum <= k with O(logN) time
				TreeSet<Integer> set = new TreeSet<Integer>();
				// add 0 to cover the single row case
				set.add(0);
				int currSum = 0;

				for (int sum : sums) {
					currSum += sum;
					// we use sum subtraction (curSum - sum) to get the subarray with sum <= k
					// therefore we need to look for the smallest sum >= currSum - k
					Integer num = set.ceiling(currSum - k);
					if (num != null)
						result = Math.max(result, currSum - num);
					set.add(currSum);
				}
			}
		}

		return result;
	}

}
