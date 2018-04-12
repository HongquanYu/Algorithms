package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RotateMatrix_7 {
	public static boolean rotateMatrix(int [][] matrix) {
		if ( matrix.length == 0 || matrix.length != matrix[0].length)
			return false;
		int n = matrix.length;
		
		for (int layer = 0; layer < n / 2; ++layer) {
			int first = layer;
			int last = n - 1 - layer;
			
			for (int i = first; i < last; ++i) {
				int offset = i - first;
				int top = matrix[first][i];
				matrix[first][i] = matrix[last - offset][first];				// left to top
				matrix[last - offset][first] = matrix[last][last - offset];	// bottom to left
				matrix[last][last - offset] = matrix[i][last];				// right to bottom
				matrix[i][last] = top;										// top to right
			}
		}
		
		return true;
	}
}
