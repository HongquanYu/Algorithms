package dp;

/** @author: Hongquan Yu
 *  @date: Mar 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumSwapsToMakeSequencesIncreasing_801 {
	
	/** DP Solution, idea is:
	 * 我们保持两个数组 prev 和 curr，分别表示到目前 i 为止进行交换的次数，prev表示当前 i 交换了，而curr
	 * 表示当前 i 没有交换。
	 * 
	 * 对于当前元素 A[i] 和 B[i] 以及前序 A[i-1] 和 B[i-1]，更新当前结果分为两种情况：
	 * 1，如果 A[i] > A[i-1] 和 B[i] > B[i-1]
	 * 2，若是 A[i] > B[i-1] 和 B[i] > A[i-1]
	 * 
	 * 优化：
	 * 对于两个 dp 数组，我们不需要用所有空间来记录每一个元素交换或者不交换的状态，我们只需要记录前一个元素就行！
	 * 所以我们可以用常数个空间就能解决，在这里，prev[0]代表不交换，prev[1]代表交换 */
	
	public int minSwap(int[] A, int[] B) {
		int[] prev = new int[]{ 0, 1 };	// number of swaps up to i, if i is swaped
		
		for (int i = 1; i < A.length; i++) {
			int[] curr = new int[] { A.length, A.length };	// number of swaps up to i, if i is not swaped
			
			if (A[i] > A[i - 1] && B[i] > B[i - 1]) {	// 两数组当前2元素递增
				curr[0] = Math.min(curr[0], prev[0]);
				curr[1] = Math.min(curr[1], prev[1] + 1);
			}
			if (A[i] > B[i - 1] && B[i] > A[i - 1]) {	// 两数组交叉递增
				curr[0] = Math.min(curr[0], prev[1]);
				curr[1] = Math.min(curr[1], prev[0] + 1);
			}
			prev = curr;
		}
		
		return Math.min(prev[0], prev[1]);
	}
	
	public static void main(String[] args) {
		int[] A = new int[] {1, 3, 5, 4};
		int[] B = new int[] {1, 2, 3, 7};
		
		MinimumSwapsToMakeSequencesIncreasing_801 m = new MinimumSwapsToMakeSequencesIncreasing_801();
		System.out.println(m.minSwap(A, B));
	}
}
