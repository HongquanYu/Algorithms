package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumSizeSubarraySum_209 {
	
	/* Two pointers */
	
	public int minSubArrayLen(int s, int[] a) {
		if (a == null || a.length == 0)
			return 0;

		int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
		while (j < a.length) {
			sum += a[j++];
			while (sum >= s) {
				min = Math.min(min, j - i);
				sum -= a[i++];
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	
	/*
	 * Since the given array contains only positive integers, the subarray sum can only increase by
	 * including more elements. Therefore, you don't have to include more elements once the current
	 * subarray already has a sum large enough. This gives the linear time complexity solution by
	 * maintaining a minimum window with a two indices.
	 * 
	 * As to NLogN solution, logN immediately reminds you of binary search. In this case, you cannot
	 * sort as the current order actually matters. How does one get an ordered array then? Since all
	 * elements are positive, the cumulative sum must be strictly increasing. Then, a subarray sum
	 * can expressed as the difference between two cumulative sum. Hence, given a start index for
	 * the cumulative sum array, the other end index can be searched using binary search.
	 */
	
    public int minSubArrayLen2(int s, int[] nums) {
        return solveNLogN(s, nums);
    }
    
    private int solveN(int s, int[] nums) {
    	
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
        
        while (end < nums.length) {
            while (end < nums.length && sum < s) 		// sliding window 的和到达s
            		sum += nums[end++];
            
            if (sum < s) 	// Total sub cannot reach s!
            		break;
            
            while (start < end && sum >= s)			// 
            		sum -= nums[start++];
            
            minLen = Math.min(minLen, end - start + 1);
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    /* 将原数组累加起来，然后再来做二叉搜索 */

    private int solveNLogN(int s, int[] nums) {
    	
        int[] sums = new int[nums.length + 1];		// auxiliary array starts from 1
        
        for (int i = 1; i < sums.length; i++) 
        		sums[i] = sums[i - 1] + nums[i - 1];
        
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < sums.length; i++) {
        	
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            
            if (end == sums.length)
            		break;
            if (end - i < minLen)
            		minLen = end - i;
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
           int mid = (lo + hi) / 2;
           if (sums[mid] >= key){
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
        }
        return lo;
    }
	
}
