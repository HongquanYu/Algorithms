package utility;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class Swap {
	public void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
	public void swap(char[] chars, int i, int j) {
		char t = chars[i];
		chars[i] = chars[j];
		chars[j] = t;
	}
}
