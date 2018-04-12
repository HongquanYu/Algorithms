package utility;

/** @author: Hongquan Yu
 *  @date: Mar 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class printMatrix {
	public void print(int[][] grid) {
		for (int i = 0; i < grid.length; ++i) {
			System.out.print("\t");
			for (int j = 0; j < grid[0].length; ++j)
				System.out.print(grid[i][j] + ", ");
			System.out.println();
		}
		System.out.println(" -------------------------------- ");
	}
}
