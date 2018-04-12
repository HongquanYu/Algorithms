package comparison;

/** @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ShellSort {
	public void sort(int[] nums) {
		int N = nums.length;
		
		int h = 1;
		while (h < N / 3)	h= h*3 + 1;
		
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
					int t = nums[j];
					nums[j] = nums[j - h];
					nums[j - h] = t;
				}
			}
			h /= 3;
		}
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
        		System.out.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		ShellSort ss = new ShellSort();
		
		int[] ar = {9, -8, 7, -6, 5, -4, 3, -2, 1, 0, 10, -11, 12};
		ss.sort(ar);
		show(ar);
	}
}
