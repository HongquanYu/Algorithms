package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MedianOfTwoSortedArrays_4 {
	public double findMedianSortedArrays(int[] A, int[] B) {
		int M = A.length;
		int N = B.length;
		if (M > N) { 			// to ensure m <= n
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = M;
			M = N;
			N = tmp;
		}
		
		int minA = 0, maxA = M, halfLen = (M + N + 1) / 2;
		while (minA <= maxA) {
			int midA = (minA + maxA) / 2;		// Middle of Array A
			int midB = halfLen - midA;			// Middle of Array B
			if (midA < maxA && B[midB - 1] > A[midA]) {			// i is too small
				minA = minA + 1; 			
			} else if (midA > minA && A[midA - 1] > B[midB]) {	// i is too big
				maxA = maxA - 1; 			
			} else { 									// i is perfect to divide the two arrays
				int maxLeft = 0;
				if (midA == 0) {			// 
					maxLeft = B[midB - 1];
				} else if (midB == 0) {
					maxLeft = A[midA - 1];
				} else {
					maxLeft = Math.max(A[midA - 1], B[midB - 1]);
				}
				
				if ((M + N) % 2 == 1) {		// Odd number, then maxLeft is the median
					return maxLeft;
				}

				int minRight = 0;
				if (midA == M) {
					minRight = B[midB];
				} else if (midB == N) {
					minRight = A[midA];
				} else {
					minRight = Math.min(B[midB], A[midA]);
				}

				return (maxLeft + minRight) / 2.0;		// Even number, then median is the average of two
			}
		}
		
		return 0.0;
	}
}
