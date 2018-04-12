package array;

import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Mar 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ToeplitzMatrix_766 {
	
	/**  */
	
	public boolean isToeplitzMatrix(int[][] matrix) {
		Map<Integer, Integer> groups = new HashMap<>();
		
		for (int r = 0; r < matrix.length; ++r) {
			for (int c = 0; c < matrix[0].length; ++c) {
				if (!groups.containsKey(r - c))
					groups.put(r - c, matrix[r][c]);
				else if (groups.get(r - c) != matrix[r][c])
					return false;
			}
		}
		
		return true;
	}
	
	public boolean isToeplitzMatrix2(int[][] matrix) {
		for (int r = 0; r < matrix.length; ++r)
			for (int c = 0; c < matrix[0].length; ++c)
				if (r > 0 && c > 0 && matrix[r - 1][c - 1] != matrix[r][c])
					return false;
		
		return true;
	}
}
