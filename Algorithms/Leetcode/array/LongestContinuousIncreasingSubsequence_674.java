package array;

/** @author: Hongquan Yu
 *  @date: Jan 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LongestContinuousIncreasingSubsequence_674 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int maxLen = 1, len = 1;
    		
        for (int i = 1; i < nums.length; ++i) {
        		len = (nums[i - 1] < nums[i]) ? len + 1 : 1;
        		maxLen = Math.max(maxLen, len);
        }
    		
        return maxLen;
    }
}
