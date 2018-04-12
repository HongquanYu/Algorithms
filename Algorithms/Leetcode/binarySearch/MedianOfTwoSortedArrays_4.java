package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MedianOfTwoSortedArrays_4 {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length, n2 = nums2.length;

		if (n1 > n2)
			return findMedianSortedArrays(nums2, nums1);
		
		int half = (n1 + n2 + 1) / 2;	// middle of merged array
		int l = 0, r = n1;		// left and right end of search boundary of nums1; right boundary should be N1!! not n1 - 1!!!
		
		// Binary Search to find the cut point of nums1 array
		while (l < r) {
			int m1 = l + (r - l) / 2, m2 = half - m1;
			
			// HERE IT IS VERY EASY TO BE ARRAY INDEX OVERFLOW!!!!
			if (nums1[m1] < nums2[m2 - 1])
				l = m1 + 1;
			else
				r = m1;
		}	// Exit: l = r and equal to middle of merged array
		
		int m1 = l, m2 = half - m1;

		int maxLeft = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
						m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

		if ((n1 + n2) % 2 == 1)												// ODD number of elements
			return maxLeft;

		int minRight = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],	
						m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);

		return (maxLeft + minRight) / 2.0;
	}
	
	/* Basic idea: find kth largest element, make k to the position of median!
	 * 
	 * In order to solve this question, we need to first understand what a median is. A median is
	 * the middle value of a dataset. Since we have 2 separately sorted array in this question, to
	 * find the middle value is somewhat complicated. However, keep in mind that we do not care
	 * about the actual value of the numbers, what we want is the middle point from the combination
	 * of 2 arrays. In other words, we are looking for the middle index of the 2 arrays. Thus
	 * approach like binary search could be employed. 
	 * 
	 * Based on the fact that the 2 arrays are sorted separately, we could try to get the sub-median 
	 * of the 2 arrays in each round. Than compare them. And the basic idea is that the left half of
	 * the array with a smaller sub-median can never contains the common median. */

	public double findMedianSortedArrays2(int[] A, int[] B) {
		int m = A.length, n = B.length;
		int l = (m + n + 1) / 2;
		int r = (m + n + 2) / 2;
		return (findKth(A, 0, B, 0, l) + findKth(A, 0, B, 0, r)) / 2.0;
	}

	public double findKth(int[] A, int startA, int[] B, int startB, int k) {
		
		if (startA >= A.length) 		return B[startB + k - 1];
		if (startB >= B.length) 		return A[startA + k - 1];
		
		if (k == 1) 		return Math.min(A[startA], B[startB]);

		int midA = Integer.MAX_VALUE, midB = Integer.MAX_VALUE;
		
		if (startA + k / 2 - 1 < A.length)
			midA = A[startA + k / 2 - 1];
		if (startB + k / 2 - 1 < B.length)
			midB = B[startB + k / 2 - 1];

		if (midA < midB)		// 谁小丢掉谁的小的那半
			return findKth(A, startA + k / 2, B, startB, k - k / 2);		// Check: rightA + leftB
		else
			return findKth(A, startA, B, startB + k / 2, k - k / 2);		// Check: rightB + leftA
	}
}
