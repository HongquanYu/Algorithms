package binarySearch;

/** @author: Hongquan Yu
 *  @date: Jan 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu	
 */
public class LongestIncreasingSubsequence_300 {
	
	/* Quadratic solution */
	
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        
        int n = nums.length, max = 1;
        int[] len = new int[n];
        
        for (int i = 0; i < n; ++i) {
            len[i] = 1;
            for(int j = 0; j < i; ++j) {
                if (nums[i] > nums[j] && len[i] < len[j] + 1) {
                    len[i] = len[j] + 1;
                }
            }
            max = Math.max(max, len[i]);
        }
        
        return max;
    }
    
    /* logarithmetic solution 
     * 
     * (1) if x is larger than all tails, append it, increase the size by 1
	 * (2) if tails[i-1] < x <= tails[i], update tails[i] */ 
    
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];		// the smallest tail of all increasing subsequences
        int size = 0;
        
        for (int x : nums) {
            int l = 0, r = size;
            
            while (l != r) {
                int m = (l + r) / 2;
                if (tails[m] < x)
                    l = m + 1;
                else
                    r = m;
            }	// exit, l == r
            
            tails[l] = x;
            
            if (l == size) ++size;
        }
        
        return size;
    }
}
