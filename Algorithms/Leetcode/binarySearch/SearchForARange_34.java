package binarySearch;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SearchForARange_34 {
	public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length < 1)
			return new int[] {-1, -1};
		
		int[] ret = new int[] {-1, -1};
		int i = 0, j = nums.length - 1;
		while (i < j) {
			int mid = i + (j - i) / 2;
			if (nums[mid] < target)
				i = mid + 1;
			else
				j = mid;
		}
		if (nums[i] != target)
			return ret;
		else
			ret[0] = i;

		j = nums.length - 1;
		while (i < j) {
			int mid = i + (j - i) / 2 + 1;
			if (nums[mid] > target)
				j = mid - 1;
			else
				i = mid;
		}
		ret[1] = j;
		return ret;
	}
	
	public int[] searchRange3(int[] nums, int target) {
		if (nums == null || nums.length < 1)
			return new int[] {-1, -1};
		
		int[] ret = new int[] {-1, -1};
		int i = Arrays.binarySearch(nums, target);
		if (nums[i] != target)
			return ret;
		else
			ret[0] = i;

		ret[1] = Arrays.binarySearch(nums, i, nums.length - 1, target);
		
		return ret;
	}
	
	public int[] searchRange2(int[] A, int target) {
		int start = firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[] {-1, -1};
		}
		return new int[] {start, firstGreaterEqual(A, target + 1) - 1};
	}

	// find the first number that is greater than or equal to target.
	// could return A.length if target is greater than A[A.length-1].
	// actually this is the same as lower_bound in C++ STL.
	private static int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			// low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				// should not be mid-1 when A[mid]==target.
				// could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}
}
