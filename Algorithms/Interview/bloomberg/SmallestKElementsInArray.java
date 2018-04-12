package bloomberg;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SmallestKElementsInArray {
	
	// 返回最小两个数的 index
	public int[] findSmallestTwo(int[] nums) {
		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;
		
		for (int i : nums) {
			if (i < first) {
				second = first;
				first = i;
			} else if (i < second)
				second = i;
		}
		
		return new int[] { first, second };
	}
	
	/** What if it is k smallest elements in the array ? */
	
	public int[] findKSmallests(int[] nums, int k) {
		Arrays.sort(nums);
/*		if (nums == null || nums.length < k)
			return null;
		
		int lo = 0, hi = nums.length - 1;
		
		while (lo < hi) {
			int p = partition(nums, lo, hi);
			if (p < k)
				lo = p + 1;
			else if (p > k)
				hi = p - 1;
			else
				break;
		}
		
		Arrays.sort(nums, 0, k);*/
		
		return Arrays.copyOfRange(nums, 0, k);
	}
	
	private int partition(int[] nums, int lo, int hi) {
		int l = lo, r = hi + 1;
		int p = lo;
		
		while (true) {
			while (l < hi && nums[++l] < nums[p] ) ;
			while (r > lo && nums[--r] > nums[p]) ;
			if (l >= r)	break;
			exch(nums, l, r);
		}
		exch(nums, r, p);
		
		return l;
	}
	
	private void exch(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] { 34, 64, 63, 23, 43, 16, 62 };
		
		SmallestKElementsInArray n = new SmallestKElementsInArray();
		
		for (int i : n.findSmallestTwo(nums))
			System.out.print(i + ", ");
		System.out.println();
		
		for (int i : n.findKSmallests(nums, 4))
			System.out.print(i + ", ");
	}
}
