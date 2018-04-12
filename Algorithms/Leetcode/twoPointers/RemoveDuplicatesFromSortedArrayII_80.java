package twoPointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveDuplicatesFromSortedArrayII_80 {
	public int removeDuplicates(int[] nums) {
		int i = 0;
		for (int n : nums)
			if (i < 2 || n > nums[i - 2])
				nums[i++] = n;
		return i;
	}

	/*
	 * I think both Remove Duplicates from Sorted Array I and II could be solved in a consistent and
	 * more general way by allowing the duplicates to appear k times (k = 1 for problem I and k = 2
	 * for problem II). Here is my way: we need a count variable to keep how many times the
	 * duplicated element appears, if we encounter a different element, just set counter to 1, if we
	 * encounter a duplicated one, we need to check this count, if it is already k, then we need to
	 * skip it, otherwise, we can keep this element. The following is the implementation and can
	 * pass both OJ:
	 */
	
	public int removeDuplicates(int A[], int n, int k) {

		if (n <= k)
			return n;

		int i = 1, j = 1;
		int cnt = 1;
		while (j < n) {
			if (A[j] != A[j - 1]) {
				cnt = 1;
				A[i++] = A[j];
			} else {
				if (cnt < k) {
					A[i++] = A[j];
					cnt++;
				}
			}
			++j;
		}
		return i;
	}
}
