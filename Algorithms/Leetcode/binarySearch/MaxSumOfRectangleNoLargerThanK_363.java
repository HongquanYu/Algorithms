package binarySearch;

import java.util.TreeSet;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaxSumOfRectangleNoLargerThanK_363 {
	public int maxSumSubmatrix(int[][] matrix, int k) {
		// 2D Kadane's algorithm + 1D maxSum problem with sum limit k
		// 2D subarray sum solution

		if (matrix.length == 0)
			return 0;

		int M = matrix.length, N = matrix[0].length;
		int result = Integer.MIN_VALUE;

		// outer loop should use smaller axis
		// now we assume we have more rows than cols, therefore outer loop will be based on cols
		for (int left = 0; left < N; left++) {
			int[] sums = new int[M];	// array that accumulate sums for each row from left to right
			for (int right = left; right < N; right++) {
				// update sums[] to include values in curr right col
				for (int i = 0; i < M; i++)
					sums[i] += matrix[i][right];

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
