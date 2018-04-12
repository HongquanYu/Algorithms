package comparison;

/**
 * @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: 1, Time: N*lgN, Worst: N^2 */

public class QuickSort {
	public void sort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}
	
	private void sort(int[] nums, int lo, int hi) {
		if (lo >= hi)
			return;
		
		int p = partition(nums, lo, hi);		// INDEX p gets its item in final
		
		sort(nums, lo, p - 1);
		sort(nums, p + 1, hi);
	}

	private int partition(int[] nums, int lo, int hi) {	// Return index !!!!
		int l = lo, r = hi + 1;
		int p = nums[lo];
		
		while (true) {
			while (nums[++l] < p && l != hi)	; 	// find an item larger than p left to right
			
			while (p < nums[--r] && r != lo)	; 	// find an item less than p right to left

			/* The above two loops might make the two pointers crossed which l is larger one than p
			 * r is less one than p. So break the outer loop and exchange p with the smaller one
			 * which is r */
			
			if (l >= r) 	break;
			
			exch(nums, l, r);	
		}
		
		exch(nums, lo, r);		// i == j or i or j reach border. when r reaches lower border which is lo!
		
		return r;		// INDEX r which means position r are in right place!
	}
	
	private void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

    private void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
        		System.out.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		
		int[] ar = {139, 23, 94, 236, 2836, 1, 139, 8, 7286, 28392873};
		qs.sort(ar);
		qs.show(ar);
	}
}
