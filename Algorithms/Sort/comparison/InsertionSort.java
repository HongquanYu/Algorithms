package comparison;

/** @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: 1, Time: N^2, Worst: N^2 */

public class InsertionSort {
	public static int[] sort(int[] nums) {
		int tmp = 0;
		for (int i = 1; i < nums.length; i++) {
			tmp = nums[i];
			int j = i - 1;
			for (; j >= 0 && tmp < nums[j]; j--)
				nums[j + 1] = nums[j];
			nums[j + 1] = tmp;
		}
		
		return nums;
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
        		System.out.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		InsertionSort ms = new InsertionSort();
		
		int[] ar = {23, 94, 236, 2836, 1, 139, 8, 7286, 28392873};
		show(ms.sort(ar));
	}
}
