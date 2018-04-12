package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ZeroMatrix_8 {
	
	/* 解法一是用两个数组来记录；下面是优化过的 */
	
	public void setZeroes(int[][] matrix) {
		int col0 = 1;	// 标记第一列是否应该设为0
		int rows = matrix.length;
		if (rows == 0 || matrix == null)
			return;
		int cols = matrix[0].length;
		
		// 第一次遍历将若是单元格是0，就将其行和列的第一个标记为0
		for (int i = 0; i < rows; i++) {		// 遍历行
			if (matrix[i][0] == 0)
				col0 = 0;
			for (int j = 1; j < cols; j++)	// 遍历除第一列的所有列
				if (matrix[i][j] == 0)
					matrix[i][0] = matrix[0][j] = 0;		// 行和列的第一个设为0
		}
		
		// 从最后的行和列来设置单元格为0
		for (int i = rows - 1; i >= 0; i--) {	// 逆序遍历行
			for (int j = cols - 1; j >= 1; j--)	// 逆序遍历列到只剩第1列
				if (matrix[i][0] == 0 || matrix[0][j] == 0)	// 行首或列首有一个为0则为0
					matrix[i][j] = 0;
			// 遍历完成后把第一个也设为0
			if (col0 == 0)	
				matrix[i][0] = 0;
		}
	}
}
