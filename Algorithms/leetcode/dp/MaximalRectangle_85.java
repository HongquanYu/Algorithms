package dp;

import java.util.Arrays;

public class MaximalRectangle_85 {
	public static int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0)
			return 0;

		int c = matrix[0].length;
		int left[] = new int[c], right[] = new int[c], height[] = new int[c];
		Arrays.fill(right, c); // left and height will be default having values 0
		int maxA = Integer.MIN_VALUE;

		for (int i = 0; i < matrix.length; i++) { 		// traverse all the rows
			int curLeft = 0, curRight = c - 1;			// default to left start and right start

			for (int j = 0; j < c; j++) 				// compute height (can do this from either side)
				height[j] = (matrix[i][j] == '1') ? height[j] + 1 : 0;

			for (int j = 0; j < c; j++) { 				// compute left (from left to right)
				if (matrix[i][j] == '1')
					left[j] = Math.max(left[j], curLeft);
				else {
					left[j] = 0;
					curLeft = j + 1;
				}
			}

			for (int j = c - 1; j >= 0; j--) { 			// compute right (from right to left)
				if (matrix[i][j] == '1')
					right[j] = Math.min(right[j], curRight);
				else {
					right[j] = c - 1;
					curRight = j - 1;
				}
			}

			for (int j = 0; j < c; j++) 				// compute the area of rectangle (can do from either side)
				maxA = Math.max(maxA, (right[j] - left[j] + 1) * height[j]);
			
			System.out.println("left: ");
			PrintMatrix(left);
			System.out.println("right: ");
			PrintMatrix(right);
			System.out.println("height: ");
			PrintMatrix(height);
			System.out.println("---------------------------------------------------------------------------");
		}
		

		
		return maxA;
	}
	
	public static void PrintMatrix(int[] m) {
		System.out.println();
		
		for (int i : m) {
			System.out.print(", " + i);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		char[][] p = {{0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}};
		maximalRectangle(p);
	}
}
