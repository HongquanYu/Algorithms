package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindKCosestElements_658 {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		List<Integer> res = new ArrayList<>();
		if (arr[0] > x) {											// first k elements
			copyOfRange(arr, res, 0, k - 1);
		} else if (arr[arr.length - 1] < x) {						// last k elements
			copyOfRange(arr, res, arr.length - k, arr.length);
		} else {														// we get a range and shrink it to k
			int idx = Arrays.binarySearch(arr, x);
			if (idx < 0)
				idx = -(idx + 1);
			int low  = Math.max(idx - k - 1, 0);
			int high = Math.min(idx + k - 1, arr.length - 1);
			
			while (high - low > k - 1) {				// shrink the range using the closest standard: shrink the side far from idx
				if (low < 0 || (x - arr[low]) <= (arr[high] - x))	
					high--;
				else if (high > arr.length - 1 || (x - arr[low]) > (arr[high] - x))
					low++;
			}
			copyOfRange(arr, res, low, high);
		}

		return res;
	}
	
	private void copyOfRange(int[] arr, List<Integer> list, int low, int high) {
		for (int i = low; i <= high; ++i)
			list.add(arr[i]);
	}
}
