package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SearchA2DMatrixII_240 {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
			return false;

		int col = matrix[0].length - 1;		// Max index of column
		int row = 0;							// Lowest row number
		while (col >= 0 && row <= matrix.length - 1) {
			if (target == matrix[row][col]) {
				return true;
			} else if (target < matrix[row][col]) {		// target is in the left side
				col--;
			} else if (target > matrix[row][col]) {
				row++;
			}
		}
		
		return false;
	}
}
