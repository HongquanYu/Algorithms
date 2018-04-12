package unionFind;

/** @author: Hongquan Yu
 *  @date: Jan 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NumberOfIslands_200 {
	public int numIslands(char[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		int r = grid.length, c = grid[0].length;
		UF uf = new UF(r, c, grid);

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == '0')
					continue;
				int p = i * c + j;
				int q;
				if (i > 0 && grid[i - 1][j] == '1') {		// upward
					q = p - c;
					uf.union(p, q);
				}
				if (i < r - 1 && grid[i + 1][j] == '1') {	// downward
					q = p + c;
					uf.union(p, q);
				}
				if (j > 0 && grid[i][j - 1] == '1') {		// left
					q = p - 1;
					uf.union(p, q);
				}
				if (j < c - 1 && grid[i][j + 1] == '1') {	// right
					q = p + 1;
					uf.union(p, q);
				}
			}
		}
		
		return uf.count;
	}
}
