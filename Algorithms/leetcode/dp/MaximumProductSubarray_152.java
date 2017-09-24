package dp;

public class MaximumProductSubarray_152 {
	
	/**
	 * The generation of max product from previous computation
	 * From the local max value or min value.
	 * So, we use two pointers to tracing possible factor that generates maximum product
	 * 
	 */
	
	public int maxProduct1(int[] nums) {
		if (nums.length == 0)
			return 0;
		
		int localMax, localMin;
		int globalMax = nums[0];
		int preLocalMax = nums[0];
		int preLocalMin = nums[0];
		
		for (int i = 1; i < nums.length; ++i) {
			
			localMax = Math.max(Math.max(preLocalMax * nums[i], preLocalMin * nums[i]), nums[i]);
			localMin = Math.min(Math.min(preLocalMax * nums[i], preLocalMin * nums[i]), nums[i]);
			globalMax = Math.max(localMax, globalMax);
			preLocalMax = localMax;
			preLocalMin = localMin;
		}
		
		return globalMax;
	}
	
	
	public int maxProduct2(int A[]) {

		int r = A[0]; // store the result that is the max we have found so far

		// imax/imin - max/min product of subarray that ends with the current number A[i]
		
		for (int i = 1, imax = r, imin = r; i < A.length; i++) {
			// multiplied by a negative makes big number smaller, small number bigger, 
			// so we redefine the extremums by swapping them
			if (A[i] < 0) {
				int t = imax;
				imax = imin;
				imin = t;
			}

			// max/min product for the current number is either the current number itself
			// or the max/min by the previous number times the current one
			
			imax = Math.max(A[i], imax * A[i]);
			imin = Math.min(A[i], imin * A[i]);

			// the newly computed max value is a candidate for our global result
			r = Math.max(r, imax);
		}

		return r;
	}
}
