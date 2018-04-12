package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SearchPeakInMountainArray {
	public int search(int[] nums) {
		if (nums == null)
			return -1;
		int lo = 0, hi = nums.length;
		
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] > nums[lo])
				lo = mid;
			else
				hi = mid - 1;
		}
		return nums[lo];
	}
	
	public static void main(String[] args) {
		int[] num = new int[] { 4, 5, 6, 7, 0, 1, 2 };
		int[] num2 = new int[] { 2, 2, 2, 2, 2 };
		int[] num3 = new int[] { 4, 5, 6, 7, 8, 9};
		
		
		SearchPeakInMountainArray s = new SearchPeakInMountainArray();
		
//		System.out.println(s.search(num2));
		System.out.println(s.search(num3));
	}
}
