package dp;

public class MaximumSubarray_53 {
	/* Bottom-up dynamic programming
	 * 
	 * dp[i] - maximum sub-array up to nums[i]
	 * 
	 * dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
	 * It can also be translated to following equation:
	 * maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i];
	 * 
	 * 总结这个递推方程的意思就是，最大子序列开始的地方是正的！
	 * 如果之前的序列都为负，直接discard，从现在的index从新开始计数
	 * 因为为负无论如何都会拉低当前序列的sum
	 */
	
	public int maxSubArray1(int[] nums) {
		
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		for (int p : nums) {
			sum = (sum > 0) ? (sum + p) : p;
			max = Math.max(sum, max);
		}
		
		return max;
	}
	
	public int maxSubArray2(int[] A) {
		
		int maxSoFar = A[0], maxEndingHere = A[0];
		
		for (int i = 1; i < A.length; ++i) {
			maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		
		return maxSoFar;
	}
}
