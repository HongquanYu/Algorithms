package twoPointers;

/** @author: Hongquan Yu
 *  @date: Mar 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SubarrayProductLessThanK_713 {
	
	/** 滑动窗口 Sliding Window Solution */
	
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int cnt = 0;
        int pro = 1;
        
        for (int l = 0, r = 0; r < nums.length; r++) {
            pro *= nums[r];
            while (l <= r && pro >= k) {		// 左边移除，做除法
                pro /= nums[l++];
            }
            cnt += r - l + 1;	// 当前 subarray 的新增加的个数
        }
        
        return cnt;        
    }
}
