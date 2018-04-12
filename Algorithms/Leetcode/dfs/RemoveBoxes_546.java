package dfs;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveBoxes_546 {
	
	/** Top-down DP - 24 ms 
	 * dp[i][j][k] 代表区间 [i, j] 上能获取的最大积分， k表示起始点 i 的左边有几个和 boxes[i] 相等。
	 * 原问题就变成了求 dp[0][n-1][0] 的解了。
	 * 我们能推出来 dp[i][i][k] = (1+k) * (1+k)， 
	 * */
	
	public int removeBoxes(int[] boxes) {
		int N = boxes.length;
		int[][][] dp = new int[N][N][N];
		return helper(boxes, 0, N - 1, 0, dp);
	}
	
	private int helper(int[] boxes, int i, int j, int k, int[][][] dp) {
		if (i > j) 	return 0;
		
		if (dp[i][j][k] > 0)		// 已经计算过了，直接返回
			return dp[i][j][k];
		
		// optimization: all boxes of the same color counted continuously from the first box should be grouped together
		for (; i + 1 <= j && boxes[i + 1] == boxes[i]; i++, k++) ;

		// 情况1，直接移除字符 i，使搜索区间变成 [i + 1, j]，也就是求 dp[i + 1][j][0]
		int res = (k + 1) * (k + 1) + helper(boxes, i + 1, j, 0, dp);
		
		// 情况2，移除 [i + 1, m - 1] 的字符使得，字符 i 和 m 连起来。
		for (int m = i + 1; m <= j; m++)
			if (boxes[i] == boxes[m])
				res = Math.max(res, helper(boxes, i + 1, m - 1, 0, dp) + helper(boxes, m, j, k + 1, dp));

		dp[i][j][k] = res;		// 做好缓存，避免重复计算
		
		return res;
	}
	
	/** Bottom Up DP - 148 ms */
	
	public int removeBoxes2(int[] boxes) {
		int n = boxes.length;
		int[][][] dp = new int[n][n][n];

		for (int j = 0; j < n; j++) {
			for (int k = 0; k <= j; k++) {
				dp[j][j][k] = (k + 1) * (k + 1);
			}
		}

		for (int l = 1; l < n; l++) {
			for (int j = l; j < n; j++) {
				int i = j - l;

				for (int k = 0; k <= i; k++) {
					int res = (k + 1) * (k + 1) + dp[i + 1][j][0];

					for (int m = i + 1; m <= j; m++)
						if (boxes[m] == boxes[i])
							res = Math.max(res, dp[i + 1][m - 1][0] + dp[m][j][k + 1]);
						
					dp[i][j][k] = res;
				}
			}
		}

		return (n == 0 ? 0 : dp[0][n - 1][0]);
	}
	
	/** Third Solution */
	
	public int removeBoxes3(int[] boxes) {
		// preprocessing, [1,1,1,3,3,2,3,3,3,1,1] would become
		List<Integer> colors = new ArrayList<>(); 		// colors : [1,3,2,3,1]
		List<Integer> lens = new ArrayList<>(); 			// lens : [3,2,1,3,2]

		for (int box : boxes) {
			if (!colors.isEmpty() && colors.get(colors.size() - 1) == box) { // continuous
				lens.set(lens.size() - 1, lens.get(lens.size() - 1) + 1);
			} else { // new color
				colors.add(box);
				lens.add(1);
			}
		}

		int N = boxes.length;
		int M = colors.size();
		// dp[i][j][k] means the maximal score for colors[i:j] with k boxes of
		// same color merged after j
		// i and j are inclusive, so dp[0][M - 1][0] will be the final answer
		int[][][] dp = new int[M][M][N];

		return dfs(colors, lens, 0, M - 1, 0, dp);
	}
	    
    /** top-down dfs search with memoization */
   
	private int dfs(List<Integer> colors, List<Integer> lens, int l, int r, int k, int[][][] dp) {
		if (l > r)
			return 0;
		if (dp[l][r][k] > 0)
			return dp[l][r][k];
		// merging boxes with colors[r]
		int score = dfs(colors, lens, l, r - 1, 0, dp) + (lens.get(r) + k) * (lens.get(r) + k);
		// merge boxes with colors[l:i] and colors[l + 1:r - 1] where i from l to r - 1
		for (int i = l; i < r; i++) {
			if (colors.get(i) == colors.get(r)) {
				// notice here : since we use top-down approach, colors[i + 1:r
				// - 1] has already been merged, so k = 0;
				// so color[i] is adjacent to color[r] now
				score = Math.max(score,
						dfs(colors, lens, l, i, lens.get(r) + k, dp) + dfs(colors, lens, i + 1, r - 1, 0, dp));
			}
		}
		dp[l][r][k] = score;
		
		return score;
	}
}
