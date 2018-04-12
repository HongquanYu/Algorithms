package divideAndConquer;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class KthLargestElementInAnArray_215 {
	
	/* Drawback of partition is that if the array is in order, then the run time complexity 
	 * becomes to quadratic! The improvement to guarantee logrithmetic is shuffle the array
	 * first and then do the partition! */
	
	public int findKthLargest(int[] nums, int k) {
		int kth = nums.length - k;		// reverse to match the index starts from 0 convention!
		int lo = 0, hi = nums.length - 1;		// lo and hi are in valid range
		
		while (lo < hi) {
			final int p = partition(nums, lo, hi);
			
			if (p < kth) 		lo = p + 1;		// kth 比当前元素大，我们需要向右靠
			else if (p > kth) 	hi = p - 1;
			else 				break;
		}
		
		return nums[kth];
	}
	
	// 这个 method 返回一个划分元素的index，使得其左边的元素都比他小，右边的元素都比他大
	private int partition(int[] a, int lo, int hi) {
		int l = lo, r = hi + 1;		// index l + 1 is first to be compared, r - 1 is first to be compared!!
		
		while (true) {
			while (l < hi && a[++l] < a[lo]) ;
			while (r > lo && a[lo] < a[--r]) ;
			
			if (l >= r) break;
			exch(a, l, r);
		}
		exch(a, lo, r);
		
		return r;
	}

	private void exch(int[] a, int i, int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
