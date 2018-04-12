package dp;

import java.util.Arrays;

public class CreateMaximumNumber_321 {
	
	/** Greedy
	 * Many of the posts have the same algorithm. In short we can first solve 2 simpler problem
	 * 
	 * 1, Create the maximum number of one array 
	 * 2, Create the maximum number of two array using all of their digits. */
	
	public int[] maxNumber(int[] A, int[] B, int k) {
		int N = A.length, M = B.length;
		int[] ans = new int[k];

		for (int i = Math.max(0, k - M); i <= k && i <= N; ++i) {
			int[] candidate = merge(singleMax(A, i), singleMax(B, k - i), k);

			if (greater(candidate, 0, ans, 0))
				ans = candidate;
		}
		return ans;
	}
	
	/** 将 A 和 B 的元素 Merge 成一个 K 位的 number */
	
	private int[] merge(int[] A, int[] B, int k) {
	    int[] ans = new int[k];
	    for (int i = 0, j = 0, r = 0; r < k; ++r)
	        ans[r] = greater(A, i, B, j) ? A[i++] : B[j++];
	        
	    return ans;
	}

	/** 检查 A[i] 是否大于 B[j] */
	
	public boolean greater(int[] A, int i, int[] B, int j) {
		int N = A.length, M = B.length;

		while (i < N && j < M && A[i] == B[j]) {
			i++;
			j++;
		}
		return j == M || (i < N && A[i] > B[j]);
	}
	
	/** 找到单个数组里面的最大的 k 位数，必须要维持开始的 order
	 * 这个实现是使用栈 stack 的模拟 */

	public int[] singleMax(int[] nums, int k) {
		int N = nums.length;
		int[] ans = new int[k];
		
		for (int i = 0, j = 0; i < N; ++i) {
			// N - i > k - j 来检查当前是否还有 digits 来比较
			while (N - i > k - j && j > 0 && ans[j - 1] < nums[i])
				j--;
			if (j < k) 	ans[j++] = nums[i];
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int[] tmp = new int[] {3, 4, 6, 5};
		int[] tmp2 = new int[] {9, 1, 2, 5, 8, 3};
		CreateMaximumNumber_321 c = new CreateMaximumNumber_321();
		
		System.out.println(Arrays.toString(c.singleMax(tmp, 1)));
		System.out.println(Arrays.toString(c.singleMax(tmp2, 4)));
	}
}
