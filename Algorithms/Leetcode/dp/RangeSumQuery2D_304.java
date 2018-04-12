package dp;

public class RangeSumQuery2D_304 {
	
	private int[][] dp;
	
	public RangeSumQuery2D_304(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return;
		
		int row = matrix.length;
		int col = matrix[0].length;
		dp = new int[row + 1][col + 1];
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
	}
    
    private static void print(int[][] m) {
    	
    	for (int i = 0; i < m.length; ++i) {
    		for (int j = 0; j < m[0].length; ++j)
    			System.out.print(m[i][j] + ", \t");
    		System.out.println();
    	}
    		
    }
    
    public static void main(String[] args) {
    	
    	int [][] matrix = {{3, 0, 1, 4, 2},
    					   {5, 6, 3, 2, 1},
    					   {1, 2, 0, 1, 5},
    					   {4, 1, 0, 1, 7},
    					   {1, 0, 3, 0, 5}};
    	
    	RangeSumQuery2D_304 r = new RangeSumQuery2D_304(matrix);
    	System.out.println("First: " + r.sumRegion(2, 1, 4, 3));	// 8
    	System.out.println("Second: " + r.sumRegion(1, 1, 2, 2));	// 11
    	System.out.println("Third: " + r.sumRegion(1, 2, 2, 4));	// 12
    }
}
