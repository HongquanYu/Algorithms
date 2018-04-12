package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class canGetExit {
	public boolean canExit(int[][] matrix, int[] start, int[] end) {
		return dfs(matrix, start, end);
	}
	
	private boolean dfs(int[][] matrix, int[] temp, int[] end) {
		int x = temp[0], y = temp[1];
		if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] == 0)
			return false;
		if (x == end[0] && y == end[1])
			return true;
		matrix[x][y] = 0;
		
		return dfs(matrix, new int[] {x + 1, y}, end) ||
				dfs(matrix, new int[] {x - 1, y}, end) ||
				dfs(matrix, new int[] {x, y + 1}, end) ||
				dfs(matrix, new int[] {x, y - 1}, end);
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] { {1, 1, 1, 0, 0, 1},
										{1, 1, 1, 0, 0, 1},
										{1, 1, 1, 0, 0, 1},
										{1, 1, 1, 1, 1, 1},
										{1, 1, 1, 0, 1, 1} };
										
		int[][] matrix1 = new int[][] { {1, 1, 1, 0, 0, 1},
										{1, 1, 1, 0, 0, 1},
										{1, 1, 1, 0, 0, 1},
										{1, 1, 1, 0, 1, 1},
										{1, 1, 1, 0, 1, 1} };
		canGetExit c = new canGetExit();
		
		System.out.println(c.canExit(matrix, new int[] {0, 0},  new int[] {4, 5}));
		System.out.println(c.canExit(matrix1, new int[] {0, 0},  new int[] {4, 5}));
	}
}
