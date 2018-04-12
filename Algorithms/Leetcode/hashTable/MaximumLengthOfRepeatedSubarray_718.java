package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utility.printMatrix;

/** @author: Hongquan Yu
 *  @date: Jan 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumLengthOfRepeatedSubarray_718 {
	public int findLength(int[] A, int[] B) {
		
		int l1 = A.length, l2 = B.length, ans = 0;
		if (l1 == 0 || l2 == 0)
			return 0;
		
		HashMap<Integer, List<Integer>> map = new HashMap<>();	// 元素和他的index们
		List<Integer> list;
		
		for (int i = 0; i < l1; i++) {		// 处理第一个数组
			int n = A[i];
			list = map.getOrDefault(n, new ArrayList<Integer>());
			list.add(i);
			map.put(n, list);
		}

		for (int i = 0; i < l2 && l2 - i > ans; i++) {	// 处理第二个数组

			if (map.containsKey(B[i])) {
				list = map.get(B[i]);
				for (int idx : list) {		// 遍历另一个数组中统一元素的索引列表
					
					if (l1 - idx < ans) break;	// 后面的元素即使全部都是相同的也没办法更新最大值
					
					int count = 1, k = idx + 1;	// count = 1表明当前A[idx] == B[i]，直接搜索下一个
					
					for (int j = i + 1; j < l2 && k < l1; j++, k++) {		// 从idx位置开始连续搜索看有多少个相同元素
						if (B[j] == A[k])	count++;
						else 				break;
					}
					
					ans = Math.max(ans, count);		// 更新最大值
				}
			}
		}

		return ans;
	}
	
	/** DP Solution: dp[i][j] = dp[i + 1][j + 1] + 1;
	 * 
	 * 共同的子数列的起始为 A[i], B[j]. 所以我们定义：
	 * dp[i][j] 是最长的prefix of A[i:] 和 B[j:]. 当A[i] == B[j], 
	 * we know dp[i][j] = dp[i+1][j+1] + 1.  */
	
	public int findLength1(int[] A, int[] B) {
		int ans = 0, N = A.length, M = B.length;
		int[][] dp = new int[N + 1][M + 1];
		
		// backwards computing
		for (int i = N - 1; i >= 0; --i) {
			for (int j = M - 1; j >= 0; --j) {
				if (A[i] == B[j]) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
					if (ans < dp[i][j])
						ans = dp[i][j];
				}
			}
		}
		new printMatrix().print(dp);
		return ans;
	}
	
	public static void main(String[] args) {
		MaximumLengthOfRepeatedSubarray_718 m = new MaximumLengthOfRepeatedSubarray_718();
		int[] A = new int[]{1,2,3,2,1};
		int[] B = new int[]{3,2,1,4,7};
		
		System.out.println(m.findLength1(A, B));
	}
}
