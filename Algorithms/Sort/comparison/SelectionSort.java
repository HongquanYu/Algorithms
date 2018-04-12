package comparison;

/** @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SelectionSort {
	public void sort (int[] nums) {
		int N = nums.length;
		int min, tmp;
		
		for (int i = 0; i < N; ++i) {
			min = i;
			for (int j = i + 1; j < N; j++)
				if (nums[min] > nums[j])
					min = j;
			
			if (min != i) {
				tmp = nums[min];
				nums[min] = nums[i];
				nums[i] = tmp;
			}
		}
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
        		System.out.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		SelectionSort ss = new SelectionSort();
		
		int[] ar = {9, -8, 7, -6, 5, -4, 3, -2, 1, 0, 10, -11, 12};
		ss.sort(ar);
		show(ar);
	}
}
