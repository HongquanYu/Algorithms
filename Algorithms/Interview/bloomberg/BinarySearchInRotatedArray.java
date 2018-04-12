package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BinarySearchInRotatedArray {
	public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
		int lo = 0, hi = nums.length - 1;

		while (lo < hi) { 		// do the binary search
			int mid = (lo + hi) / 2;
			if (nums[mid] == target)
				return mid;
			
			// 正中间旋转，奇数个元素单调递增则落在前半部分，偶数为后半部分
			if (nums[lo] <= nums[mid]) { 	// 前半部分旋转，现在前半部分是单调递增
				if (target >= nums[lo] && target < nums[mid])	// target处于递增区间
					hi = mid - 1;	// 搜索前半部分
				else 				// 搜索后半部分
					lo = mid + 1;
			} else { 						// 后半部分旋转，现在后半部分是递增区间
				if (target > nums[mid] && target <= nums[hi]) 	// 后半部分是递增区间
					lo = mid + 1;	// 搜索后半部分
				else 				// 搜索前半部分
					hi = mid - 1;
			}
		}
		
		return nums[lo] == target ? lo : -1; // return index or -1
	}
}
