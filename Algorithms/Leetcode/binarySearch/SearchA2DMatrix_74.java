package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SearchA2DMatrix_74 {
	
	/** Index Binary Search */
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0)
			return false;
		
		int start = 0;
		int rows = matrix.length, cols = matrix[0].length;
		int end = rows * cols - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;		// mid index
			
			if (matrix[mid / cols][mid % cols] < target)
				start = mid + 1;
			else if (matrix[mid / cols][mid % cols] >target)
				end = mid - 1;
			else
				return true;
		}
		
		return false;
	}
	
	/* Search the matrix from last element of first row.
	 * if current element is smaller than target, then col moves left
	 * If current element is larger than target, then row moves down */
	
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        		return false;
        
        int row = 0, col = matrix[0].length - 1;		// First and last element index.
        
        while (col >= 0 && row < matrix.length) {
        		if (matrix[row][col] > target)		// 当前元素比 target 大，向左移
        			col--;
        		else if (matrix[row][col] < target)	// 当前元素比 target 小，向下移
        			row++;
        		else 
        			return true;
        }
        
        return false;
    }
}
