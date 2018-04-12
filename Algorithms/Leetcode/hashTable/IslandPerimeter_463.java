package hashTable;

public class IslandPerimeter_463 {
	
	/** 1, 遍历matrix，island的定义是有1就是
	 *  2, 我们只需要找到有多少个小格子连接起来，并且有多少邻居
	 *  3, the result is islands * 4 - neighbours * 2 */

	public int islandPerimeter(int[][] grid) {
		int islands = 0, neighbours = 0;
		int row = grid.length, col = grid[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {		//	岛的个数加一
					islands++; 
					if (i < row - 1 && grid[i + 1][j] == 1)	// 下边是不是有岛
						neighbours++; 
					if (j < col - 1 && grid[i][j + 1] == 1)	// 右边是不是有岛
						neighbours++; 
				}
			}
		}

		return islands * 4 - neighbours * 2;
	}
}
