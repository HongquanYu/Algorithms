package wayfair;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Mar 14, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ClassifyNegativePositive {
	static void merge(int arr[], int lo, int mid, int hi) {
		int i, j, k;
		int N1 = mid - lo + 1;
		int N2 = hi - mid;

		int left[] = new int[N1];
		int right[] = new int[N2];

		for (i = 0; i < N1; i++)
			left[i] = arr[lo + i];
		for (j = 0; j < N2; j++)
			right[j] = arr[mid + 1 + j];

		i = 0;
		j = 0;

		k = lo;		
		while (i < N1 && left[i] < 0)		// Copy back negatives
			arr[k++] = left[i++];

		while (j < N2 && right[j] < 0)		// Copy back negatives
			arr[k++] = right[j++];

		while (i < N1)						// Copy back positives
			arr[k++] = left[i++];

		while (j < N2)						// Copy back positives
			arr[k++] = right[j++];
	}

	static void RearrangePosNeg(int arr[], int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;

			RearrangePosNeg(arr, lo, mid);
			RearrangePosNeg(arr, mid + 1, hi);

			merge(arr, lo, mid, hi);
		}
	}

	public static void main(String[] args) {
		int arr[] = {-12, 11, -13, -5, 6, -7, 5, -3, -6};
		int arr_size = arr.length;
		RearrangePosNeg(arr, 0, arr_size - 1);
		System.out.println(Arrays.toString(arr));
	}
}
