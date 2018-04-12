package dp;

public class MaximalSquare_221 {
	
	/** dp[i][j] 表示在点[i][j] 左上方能形成的正方形的最大边长
	 * 对于第一行第一列的值和matrix的值相同。
	 * 对于中间的列如果 matrix[i][j] = 1, 
	 * 		P[i][j] = min(P[i - 1][j], P[i][j - 1], P[i - 1][j - 1]) + 1.
	 * 如果 matrix[i][j] = 0, P[i][j] = 0
	 *  */
	
	public int maximalSquare(char[][] matrix) {
		int N = matrix.length;
		int M = N > 0 ? matrix[0].length : 0;
		
		int[][] dp = new int[N + 1][M + 1];
		int maxsqlen = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
					maxsqlen = Math.max(maxsqlen, dp[i][j]);
				}
			}
		}
		
		return maxsqlen * maxsqlen;
	}
}
