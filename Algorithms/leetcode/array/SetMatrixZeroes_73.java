package array;

import java.util.Arrays;

public class SetMatrixZeroes_73 {
	
	// --------------------------- Space(m + n) --------------------------
	
    public void setZeroes(int[][] matrix) {
    		int r = matrix.length;
    		if (r == 0 || matrix == null)
    			return;
    		
    		int c = matrix[0].length;
        int[] row = new int[r];
        int[] col = new int[c];
        Arrays.fill(row, -1);
        Arrays.fill(col, -1);
        
        for (int i = 0; i < r; ++i) {				// record all the 0s in the rows and cols
        		for (int j = 0; j < c; ++j) {
        			if (matrix[i][j] == 0) {
        				row[i] = 0;
        				col[j] = 0;
        			}
        		}
        }
        
		for (int i = 0; i < r; ++i) {
			if (row[i] == 0) {
				for (int j = 0; j < c; ++j) {
					matrix[i][j] = 0;
				}
			}
		}
		
		for (int i = 0; i < c; ++i) {
			if (col[i] == 0) {
				for (int j = 0; j < r; ++j) {
					matrix[j][i] = 0;
				}
			}
		}
    }
    
 // --------------------------- constant Space --------------------------
    
	public void setZeroes1(int[][] matrix) {
		int col0 = 1, rows = matrix.length;
		if (rows == 0 || matrix == null)
			return;
		int cols = matrix[0].length;

		for (int i = 0; i < rows; i++) {
			if (matrix[i][0] == 0)
				col0 = 0;
			for (int j = 1; j < cols; j++)
				if (matrix[i][j] == 0)
					matrix[i][0] = matrix[0][j] = 0;
		}

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 1; j--)
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			if (col0 == 0)
				matrix[i][0] = 0;
		}
	}
}
