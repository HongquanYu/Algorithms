package array;

import java.util.LinkedList;
import java.util.Queue;

public class ReshapeTheMatrix_566 {
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        if (nums == null || row == 0 || r * c != row * nums[0].length)
        		return nums;
    		
        int[] buffer = new int[r * c];
        int idx = 0;
        for (int i = 0; i < row; ++i)		// read the data into a buffer
        		for (int j : nums[i])
        			buffer[idx++] = j;
        
    		int [][] newMatrix = new int[r][c];
    		idx = 0;
    		for (int i = 0; i < r; ++i) {
    			for (int j = 0; j < c; ++j) {
    				newMatrix[i][j] = buffer[idx++];
    			}
    		}
    		
    		return newMatrix;
    }
    
    public static void main(String[] args) {
    		int[][] m = {{1, 2}, {3, 4}};
    		System.out.print(matrixReshape(m, 1, 4));
    }
    
    /* Using queue */
    
	public int[][] matrixReshape1(int[][] nums, int r, int c) {
		int[][] res = new int[r][c];
		if (nums.length == 0 || r * c != nums.length * nums[0].length)
			return nums;
		
		// All the data into queue
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				queue.add(nums[i][j]);
			}
		}
		
		// fit all the data into new matrix
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				res[i][j] = queue.remove();
			}
		}
		
		return res;
	}
	
	/* Without using extra space */
	public int[][] matrixReshape2(int[][] nums, int r, int c) {
		int[][] res = new int[r][c];
		if (nums.length == 0 || r * c != nums.length * nums[0].length)
			return nums;
		
		int rows = 0, cols = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				res[rows][cols] = nums[i][j];
				cols++;
				if (cols == c) {
					rows++;
					cols = 0;
				}
			}
		}
		
		return res;
	}
	
	/* Using division and modules */
	public int[][] matrixReshape3(int[][] nums, int r, int c) {
		int[][] res = new int[r][c];
		if (nums.length == 0 || r * c != nums.length * nums[0].length)
			return nums;
		
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				res[count / c][count % c] = nums[i][j];
				count++;
			}
		}
		
		return res;
	}
}
