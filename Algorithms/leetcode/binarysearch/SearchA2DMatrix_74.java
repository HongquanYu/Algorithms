package binarysearch;

import javax.imageio.ImageTypeSpecifier;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SearchA2DMatrix_74 {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		int start = 0, rows = matrix.length, cols = matrix[0].length;
		int end = rows * cols - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (matrix[mid / cols][mid % cols] == target) {
				return true;
			}
			if (matrix[mid / cols][mid % cols] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return false;
	}
	
	
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        		return false;
        int col = matrix[0].length - 1;
        int row = 0;
        
        while (col >= 0 && row < matrix.length) {
        		if (target == matrix[row][col])
        			return true;
        		else if (target < matrix[row][col])
        			col--;
        		else if (target > matrix[row][col])
        			row++;
        }
        
        return false;
    }
}
