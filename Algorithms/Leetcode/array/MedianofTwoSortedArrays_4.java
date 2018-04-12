package array;

public class MedianofTwoSortedArrays_4 {

	public double findMedianSortedArrays(int[] A, int[] B) {
		
		int m = A.length, n = B.length;
		
		if (m > n) { // to ensure m <= n
			return findMedianSortedArrays(B, A);
		}
		
		int lo = 0, hi = m, mid = (m + n + 1) / 2;
		
		while (lo <= hi) {
			
			int midA = (lo + hi) / 2;			// The boundary of median in array A
			int midB = mid - midA;				// The boundary of median in array B
			
			if (midA < hi && B[midB - 1] > A[midA]) {
				lo = lo + 1;
			} else if (midA > lo && A[midA - 1] > B[midB]) {
				hi = hi - 1; 			// i is too big
			} else { 							// i is perfect, we found the median boundary
				int maxLeft = 0;
				if (midA == 0) 			maxLeft = B[midB - 1];							// All elements in A are at right half of median
				else if (midB == 0) 		maxLeft = A[midA - 1];							// All elements in B are at right half of median
				else 					maxLeft = Math.max(A[midA - 1], B[midB - 1]);	// choose the max of left half part of median
				
				// Odd number of elements in two arrays
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}
				
				// Even number of elements in two arrays
				int minRight = 0;
				if (midA == m) 			minRight = B[midB];
				else if (midB == n)		minRight = A[midA];
				else 				minRight = Math.min(B[midB], A[midA]);
				
				return (maxLeft + minRight) / 2.0;
			}
		}
		
		return 0.0;
	}
}
