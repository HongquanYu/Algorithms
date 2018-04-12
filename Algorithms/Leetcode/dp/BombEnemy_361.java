package dp;

import java.util.Arrays;

public class BombEnemy_361 {
	
	private static int[][] memo;				// to store number of enemies that can be killed if bomb is in the location
	private static char[][] matrix;
	private static int row, col;
	
	/** * Brute-force Solution - 1158ms
	 * Time: O(MN), space: O(1) */
	
    public static int maxKilledEnemies(char[][] grid) {
    	int max = 0;
    	row = grid.length;
    	col = grid[0].length;
    	memo = new int[row][col];
    	matrix = grid;
    	for (int k = 0; k < row; ++k)
    		Arrays.fill(memo[k], -1);
    	
    	if (row == 0 || grid == null)
    		return max;
    	
        for (int ro = 0; ro < row; ++ro)
        	for (int co = 0; co < col; ++co) {
        		if (matrix[ro][co] == '0') {
        			 int killedEnemyNumber = findEnemy(ro, co);
        			 max = Math.max(max, killedEnemyNumber);
        		}
        	}
    	
    	return max;
    }
    
    private static int findEnemy(int r, int c) {
    	if (memo[r][c] != -1)
    		return memo[r][c];
    	
    	int rc = 0, cc = 0;
    	
    	boolean rowInWall = false, colInWall = false;
    	
    	for (int i = 0; i < col; ++i) {					// check every element in the row r
    		
    		if 		(i == c) 			{ rowInWall = true; continue; }
    		else if (rowInWall && matrix[r][i] == 'W')	break;
    		else if (matrix[r][i] == 'E')					rc++;
    		else if (!rowInWall && matrix[r][i] == 'W') 	rc = 0;
    		
    	}
    	
    	for (int i = 0; i < row; ++i) {								// check every element in the col c
    		if 		(i == r) 			{ colInWall = true; continue; }		// meet bomb
    		else if (colInWall && matrix[i][c] == 'W')	break;
    		else if (matrix[i][c] == 'E')					cc++;				// 
    		else if (!colInWall && matrix[i][c] == 'W') 	cc = 0;
    		
    	}
    	
    	System.out.println("enemies at (" + r + ", " + c + ") is " + rc + " + " + cc);
    	
    	memo[r][c] = rc + cc;
    	
    	return rc + cc;
    }
    
    /** DP Solution from discussion:
     * 
     * @param args
     */
    
    
	public int maxKilledEnemies_dp(char[][] grid) {
		if (grid.length < 1 || grid[0].length < 1)
			return 0;
		
		int[][] dp = new int[grid.length][grid[0].length];
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < grid.length; i++) {				// traverse all the rows
			int j = 0;
			while (j < grid[0].length) {	// traverse all the columns
				int count = 0, k = j;
				while (j < grid[0].length && grid[i][j] != 'W') {	// not a wall
					if (grid[i][j] == 'E')	// increase counter when encounter an enemy
						++count;
					++j;					// move pointer to the next column
				}
				while (k < j) {
					dp[i][k] = count;
					++k;
				}
				++j;
			}
		}
		
		for (int i = 0; i < grid[0].length; i++) {		// traverse all the columns
			int j = 0;
			while (j < grid.length) {
				int count = 0;
				int k = j;
				while (j < grid.length && grid[j][i] != 'W') {
					if (grid[j][i] == 'E') {
						++count;
					}
					++j;
				}
				while (k < j) {
					dp[k][i] += count;
					++k;
				}
				++j;
			}
		}
		
		for (int i = 0; i < grid.length; i++) {					// find the maximum to return 
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '0') {
					max = Math.max(dp[i][j], max);
				}
			}
		}
		
		return max == Integer.MIN_VALUE ? 0 : max;
	}
	
	/** Most voted solution
	 * 
	 * Walk through the matrix. At the start of each non-wall-streak (row-wise
	 * or column-wise), count the number of hits in that streak and remember it.
	 * O(mn) time, O(n) space. */
	
	public int maxKilledEnemies_clear(char[][] grid) {
		int m = grid.length;
		int n = (m != 0) ? grid[0].length : 0;
		int result = 0;
		int rowhits = 0;
		int[] colhits = new int[n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j == 0 || grid[i][j - 1] == 'W') {		// check rows: start point or the previous one is wall
					rowhits = 0;
					for (int k = j; k < n && grid[i][k] != 'W'; k++)	// count the valid enemy and store it
						rowhits += grid[i][k] == 'E' ? 1 : 0;
				}
				
				if (i == 0 || grid[i - 1][j] == 'W') {		// check columns: start point or the previous one is wall
					colhits[j] = 0;
					for (int k = i; k < m && grid[k][j] != 'W'; k++)	// count the valid enemy and store it
						colhits[j] += grid[k][j] == 'E' ? 1 : 0;
				}
				
				if (grid[i][j] == '0')						// a valid bomb position and update the max value
					result = Math.max(result, rowhits + colhits[j]);
			}
		}
		
		return result;
	}
    
    public static void main(String[] args) {
    	char[][] text = {{'0', 'E', '0', '0'}, 
    					{'E', '0', 'W', 'E'}, 
    					{'0', 'E', '0', '0'}};
    	
    	System.out.println("Killed enemy: " + maxKilledEnemies(text));
    }
}
