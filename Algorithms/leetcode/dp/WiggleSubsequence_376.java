package dp;

import java.util.Arrays;

public class WiggleSubsequence_376 {
	
	// ---------------------------Editorial brute force solution---------------------------------
	
	private int calculate(int[] nums, int index, boolean isUp) {
		int maxcount = 0;
		
		for (int i = index + 1; i < nums.length; i++) {
			if ((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index]))
				maxcount = Math.max(maxcount, 1 + calculate(nums, i, !isUp));
		}
		
		return maxcount;
	}

	public int wiggleMaxLength(int[] nums) {
		if (nums.length < 2)
			return nums.length;
		return 1 + Math.max(calculate(nums, 0, true), calculate(nums, 0, false));
	}
	
	// --------------------------------------Editorial DP-------------------------------------------
	
	public int wiggleMaxLength1(int[] nums) {
		if (nums.length < 2)
			return nums.length;

		int[] up = new int[nums.length];			// longest wiggle until ith char, ending with a rising wiggle
		int[] down = new int[nums.length];			// longest wiggle until ith char, ending with a falling wiggle

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				
				if (nums[i] > nums[j]) {						// find a rising wiggle, update up[i]
					up[i] = Math.max(up[i], down[j] + 1);
				}
				else if (nums[i] < nums[j]) {					// find a falling wiggle, update down[i]
					down[i] = Math.max(down[i], up[j] + 1);
				}
			}
		}
		
		return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
	}
	
	// --------------------------------------Editorial linear DP-------------------------------------------
	
	public int wiggleMaxLength2(int[] nums) {
		if (nums.length < 2)
			return nums.length;
		
		int[] up = new int[nums.length];
		int[] down = new int[nums.length];
		up[0] = down[0] = 1;				// initialization
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				up[i] = down[i - 1] + 1;
				down[i] = down[i - 1];
			} else if (nums[i] < nums[i - 1]) {
				down[i] = up[i - 1] + 1;
				up[i] = up[i - 1];
			} else {
				down[i] = down[i - 1];
				up[i] = up[i - 1];
			}
		}
		return Math.max(down[nums.length - 1], up[nums.length - 1]);
	}
	
	// ----------------------------Editorial linear DP with O(1) space-------------------------------------
	
	public int wiggleMaxLength3(int[] nums) {
		if (nums.length < 2)
			return nums.length;
		
		int down = 1, up = 1;
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1])
				up = down + 1;
			else if (nums[i] < nums[i - 1])
				down = up + 1;
		}
		return Math.max(down, up);
	}
	
	// ---------------------------- Editorial Greedy ------------------------------------
	
    public int wiggleMaxLength4(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        
        int prevdiff = nums[1] - nums[0];		//	whether the current subsequence of numbers lies in an increasing or decreasing wiggle
        int count = (prevdiff != 0) ? 2 : 1;
        
        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                count++;
                prevdiff = diff;
            }
        }
        
        return count;
    }
	
	
	
	// ---------------------------------------------------------------------------------
	/* Some test case are not passed.*/
	
    public static int wiggleMaxLength_2(int[] nums) {
    	
    	int len = nums.length;
    	if (len < 2)
    		return len;
    	if (len == 2) {
    		if (nums[len - 1] == nums[len - 2])
    			return 1;
    		return 2;
    	}
        int[] prev = new int[len];
        int[] wigCount = new int[len];
        
        Arrays.fill(wigCount, 1);
        wigCount[1] = 2;
        Arrays.fill(prev, -1);
        
        for (int i = 2; i < len; ++i) {
        	for (int j = i - 1; j > 0; --j) {
        		if (((nums[i] - nums[j]) * (nums[j] - nums[j-1])) < 0 && wigCount[i] < wigCount[j] + 1) {
        			wigCount[i] = wigCount[j] + 1;
        			prev[i] = j;
        		}
        	}
        }
        
    	return wigCount[len - 1];
    }
    
    // -----------------------------------From SMs----------------------------------------------
    
    public static int wiggleMaxLength_1(int[] nums) {
		int len = nums.length;
		if (len == 0 || len == 1)
			return len;

        /* rise:
         * 1 -- a[i] > a[i - 1]
         * 0 -- a[i] = a[i - 1]
         * -1 - a[i] < a[i - 1] */
        int rise = 0;
        boolean start = false;
        /* start:
         * true -- have always been even
         * false - have been serrated before */
        
        int res = 1;

        int pre = nums[0];					// previous element
        
		for (int i = 1; i < len; i++) {
			if (nums[i] > pre) {			// a[i] > a[i-1]
				if ((rise == -1) || !start)		// 
					res++;
				rise = 1;

				start = true;
			} else if (nums[i] < pre) {		// a[i] < a[i-1]
				if ((rise == 1) || !start)
					res++;
				rise = -1;

				start = true;
			}

			pre = nums[i];					// a[i] == a[i-1], skip it and update pre
		}

        return res;
    }
    
    
    
    public static void main(String[] args) {
    	int[] num = {3,3,3,2,5};
    	System.out.println(wiggleMaxLength_2(num));
    }
}
