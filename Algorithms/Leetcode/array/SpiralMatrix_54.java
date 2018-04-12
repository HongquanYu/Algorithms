package array;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class SpiralMatrix_54 {
	
	/** 模拟旋转 */
	
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		if (matrix.length == 0)
			return ans;
		int R = matrix.length, C = matrix[0].length;
		
		boolean[][] seen = new boolean[R][C];
		int[] dr = { 0, 1, 0, -1 };	// 行变化的 4 个方向
		int[] dc = { 1, 0, -1, 0 };	// 列变化的 4 个方向
		int r = 0, c = 0, di = 0;
		
		for (int i = 0; i < R * C; i++) {
			ans.add(matrix[r][c]);
			seen[r][c] = true;
			
			int cr = r + dr[di];
			int cc = c + dc[di];
			if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {	// 迈进到下一个
				r = cr;	c = cc;
			} else {		// 轮番来做
				di = (di + 1) % 4;
				r += dr[di];
				c += dc[di];
			}
		}
		return ans;
	}
	
	/** Layer-by-Layer 一层又一层 */
	
	public List<Integer> spiralOrdeR(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		if (matrix.length == 0)
			return ans;
		int r1 = 0, R = matrix.length - 1;
		int c1 = 0, C = matrix[0].length - 1;
		
		while (r1 <= R && c1 <= C) {
			for (int c = c1; c <= C; c++)
				ans.add(matrix[r1][c]);
			for (int r = r1 + 1; r <= R; r++)
				ans.add(matrix[r][C]);
			if (r1 < R && c1 < C) {
				for (int c = C - 1; c > c1; c--)
					ans.add(matrix[R][c]);
				for (int r = R; r > r1; r--)
					ans.add(matrix[r][c1]);
			}
			r1++;	c1++;
			R--;		C--;
		}
		return ans;
	}
}
