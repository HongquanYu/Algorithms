package comparison;

/** @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: 1, Time: N^2, Worst: N^2 */

public class BubbleSort {
	public int[] sort(int arr[]) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		
		return arr;
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
        		System.out.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		BubbleSort ms = new BubbleSort();
		
		int[] ar = {9, -8, 7, -6, 5, -4, 3, -2, 1, 0, 10, -11, 12};
		show(ms.sort(ar));
	}
}
