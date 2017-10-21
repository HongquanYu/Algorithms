package dp;

public class SplitArrayLargestSum_410 {
	
	public int splitArrayDP(int[] nums, int m) {
		int L = nums.length;
		int[] S = new int[L + 1];
		S[0] = 0;
		for (int i = 0; i < L; i++)
			S[i + 1] = S[i] + nums[i];

		int[] dp = new int[L];
		for (int i = 0; i < L; i++)
			dp[i] = S[L] - S[i];

		for (int s = 1; s < m; s++) {
			for (int i = 0; i < L - s; i++) {
				dp[i] = Integer.MAX_VALUE;
				for (int j = i + 1; j <= L - s; j++) {
					int t = Math.max(dp[j], S[j] - S[i]);
					if (t <= dp[i])
						dp[i] = t;
					else
						break;
				}
			}
		}

		return dp[0];
	}
	
	/* Binary search */
	
	public int splitArray(int[] nums, int m) {
		int max = 0;
		long sum = 0;
		for (int num : nums) {
			max = Math.max(num, max);
			sum += num;
		}
		if (m == 1)
			return (int) sum;
		// binary search
		long l = max;
		long r = sum;
		while (l <= r) {
			long mid = (l + r) / 2;
			if (valid(mid, nums, m)) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return (int) l;
	}

	public boolean valid(long target, int[] nums, int m) {
		int count = 1;
		long total = 0;
		for (int num : nums) {
			total += num;
			if (total > target) {
				total = num;
				count++;
				if (count > m) {
					return false;
				}
			}
		}
		return true;
	}
}
