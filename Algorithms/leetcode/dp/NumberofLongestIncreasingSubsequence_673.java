package dp;

public class NumberofLongestIncreasingSubsequence_673 {

	/*
	 * The idea is to use two arrays len[n] and cnt[n] to record the maximum length of Increasing
	 * Subsequence and the coresponding number of these sequence which ends with nums[i],
	 * respectively. That is:
	 * 
	 * len[i]: the length of the Longest Increasing Subsequence which ends with nums[i]. cnt[i]: the
	 * number of the Longest Increasing Subsequence which ends with nums[i].
	 * 
	 * Then, the result is the sum of each cnt[i] while its corresponding len[i] is the maximum
	 * length.
	 */

	public int findNumberOfLIS(int[] nums) {
		int n = nums.length, res = 0, max_len = 0;
		int[] len = new int[n], cnt = new int[n];
		for (int i = 0; i < n; i++) {
			len[i] = cnt[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					if (len[i] == len[j] + 1)
						cnt[i] += cnt[j];
					if (len[i] < len[j] + 1) {
						len[i] = len[j] + 1;
						cnt[i] = cnt[j];
					}
				}
			}
			if (max_len == len[i])
				res += cnt[i];
			if (max_len < len[i]) {
				max_len = len[i];
				res = cnt[i];
			}
		}
		return res;
	}
}
