package array;

/** @author: Hongquan Yu
 *  @date: Jan 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ShortestUnsortedContinuousSubarray_581 {
	
	/* Use two indices to trace start and end position of sub-array. The Intuition of this
	 * question is no element in the left side of startIndex are larger than it, nor element
	 * in the right of endIndex less than endIndex.
	 * 
	 * How do we make sure of that?
	 * 
	 * Through two iterations, we find the min and max along the way of comparison. Take finding
	 * max as an example, we set max as first element of array, iterate from the second item. If
	 * current item is larger than max, then it could not be in the sub-array because it is in a
	 * ascending sequence; However, if current item is less than max, then the order should be 
	 * re-arranged to make sure it's ascending! Since positions of elements are going to change,
	 * then we need to update endIndex to current position. Why this works? Because along the way,
	 * we are keep updating max too! This make sure the max is the biggest of all elements we past.
	 * So, if current item is larger one then there is always keep ascending! Along with the process,
	 * we can make sure the final updated endIndex is the end position of sub-array.
	 * 
	 * The similar process gonna happen when we find the start position.	*/
	
	public int findUnsortedSubarray(int[] nums) {
		
		int n = nums.length;
		int begin = -1, end = -2;		// Initialize the start and end indices.
		int min = nums[n - 1];			// Start index that means elements in the left are smaller than this and all sorted
		int max = nums[0];				// End index which indicates elements in the right are larger than current one
		
		for (int i = 1; i < n; i++) {
			max = Math.max(max, nums[i]);			// Compare item 1 to n - 1
			min = Math.min(min, nums[n - 1 - i]);	// Compare item n - 2 to 0
			
			if (nums[i] < max)			// Move end index from start to end
				end = i;
			if (nums[n - 1 - i] > min)	// Move start index from end to start
				begin = n - 1 - i;
		}
		
		return end - begin + 1;
	}
	
	// Detailed explained version
	
	public int findUnsortedSubarray2(int[] nums) {
		
		int n = nums.length;
	    
	    /* Iterate from beginning of array
	     * find the last element which is smaller than the last seen max from 
	     * its left side and mark it as end */
		
	    int max = nums[0];
	    int end = -2;
	    
	    for(int i = 1; i < n; i++){
	    	
	        max = Math.max(max, nums[i]);
	        if(nums[i] < max)
	            end = i;
	    }
	    
	    /* iterate from end of array
	     * find the last element which is bigger than the last seen min from 
	     * its right side and mark it as begin */
	    
	    int min = nums[n - 1];
	    int begin = -1;
	    
	    for(int i = n - 2; i >= 0; i--){
	    	
	        min = Math.min(min, nums[i]);
	        if(nums[i] > min)
	            begin = i;
	    }
	    
	    return end - begin + 1;
	}
}
