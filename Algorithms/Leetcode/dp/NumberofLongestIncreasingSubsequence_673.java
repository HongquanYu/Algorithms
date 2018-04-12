package dp;

public class NumberofLongestIncreasingSubsequence_673 {

	/*
	 * The idea is to use two arrays len[n] and cnt[n] to record the maximum length of Increasing
	 * Subsequence and the corresponding number of these sequence which ends with nums[i],
	 * respectively. That is:
	 * 
	 * len[i]: the length of the Longest Increasing Subsequence which ends with nums[i]. 
	 * cnt[i]: the number of the Longest Increasing Subsequence which ends with nums[i].
	 * 
	 * Then, the result is the sum of each cnt[i] while its corresponding len[i] is the maximum
	 * length.
	 */

	public static int findNumberOfLIS(int[] nums) {
		int n = nums.length, res = 0, max_len = 0;
		int[] len = new int[n], cnt = new int[n];
		
		for (int i = 0; i < n; i++) {
			len[i] = cnt[i] = 1;			// give an initial value
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {		// an increasing pair, then we update length and count array
					if (len[i] == len[j] + 1)	// Another sequence with same length ends with nums[i] we have met
						cnt[i] += cnt[j];
					if (len[i] < len[j] + 1) {	// update it with longer one so far
						len[i] = len[j] + 1;
						cnt[i] = cnt[j];
					}
				}
			}
			
			if (max_len == len[i])	// current sequence is the longest, add counts
				res += cnt[i];
			if (max_len < len[i]) {	// update to longest sequence
				max_len = len[i];
				res = cnt[i];
			}
		}
		
		return res;
	}
    
    public static void main(String [] args) {
    		int[] t = new int[] {1,2,4,3,5,4,7,2};
    		int[] t1 = new int[] { 1,3,5,4,7 };
    		
    		System.out.println("The result: " + findNumberOfLIS(t1));
    		
    }
}
