package wayfair;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Mar 14, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DutchFlag {
	
	/**  */
	
	static void sort012(int a[]) {
		int lo = 0, hi = a.length - 1;
		int ptr = 0;
		
		while (ptr <= hi) {
			if 		(a[ptr] == 0) 	swap(a, lo++, ptr++);
			else if 	(a[ptr] == 1) 	ptr++;
			else 					swap(a, ptr, hi--);
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		int arr[] = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
		sort012(arr);
		
		System.out.println(Arrays.toString(arr));
	}
}
