package dp;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle_85 {
	
	/** DP 解法
	 * left数组	 - 当前列的高度的矩形的左边界
	 * right数组	 - 当前列的高度的矩形的右边界
	 * height数组 - 记录本列里当前元素之上连续的 1 的个数 
	 * 例子：
	 * 		Matrix			Height 			Left				right
	 * 	0 0 0 1 0 0 0	0 0 0 1 0 0 0	0 0 0 3 0 0 0	7 7 7 4 7 7 7
	 *  0 0 1 1 1 0 0	0 0 1 2 1 0 0	0 0 2 3 2 0 0	7 7 5 4 5 7 7
	 *  0 1 1 1 1 1 0	0 1 2 3 2 1 0	0 1 2 3 2 1 0	7 6 5 4 5 6 7 */
	
	public static int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;
		
		int N = matrix.length, M = matrix[0].length;
		int left[] = new int[M], right[] = new int[M], height[] = new int[M];
		Arrays.fill(right, M);
		int maxArea = 0;

		for (int i = 0; i < N; i++) {
			int l = 0, r = M - 1;		// 当前行矩形的左右边界

			for (int j = 0; j < M; j++)
				height[j] = (matrix[i][j] == '1') ? height[j] + 1 : 0;

			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == '1')
					left[j] = Math.max(left[j], l);
				else {
					left[j] = 0;
					l = j + 1;
				}
			}

			for (int j = M - 1; j >= 0; j--) {
				if (matrix[i][j] == '1')
					right[j] = Math.min(right[j], r);
				else {
					right[j] = M - 1;
					r = j - 1;
				}
			}

			for (int j = 0; j < M; j++)
				maxArea = Math.max(maxArea, (right[j] - left[j] + 1) * height[j]);
		}
		
		return maxArea;
	}
	
	/** Solution 2: 
	 * 对每一行进行调用 histogram 算法 */
	
	public int maximalRectangle1(char[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;
		int M = matrix[0].length;
		int[] heights = new int[M];	// successive '1's above this row (inclusive)
		int max = 0;
		for (char[] row : matrix) {
			for (int i = 0; i < M; i++) {
				if (row[i] == '1') 	heights[i] += 1;
				else 				heights[i] = 0;
			}

			max = Math.max(max, maxArea(heights));
		}
		return max;
	}

	/** Exact same solution to Histogram */
	
	private int maxArea(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i <= heights.length; i++) {
			int h = (i == heights.length) ? 0 : heights[i];
			while (!stack.isEmpty() && heights[stack.peek()] > h) {
				int leftBound = -1;
				if (!stack.isEmpty())
					leftBound = stack.peek();
				
				max = Math.max(max, heights[stack.pop()] * (i - leftBound - 1));
			}
			stack.push(i);
		}
		return max;
	}
	
	public static void main(String[] args) {
		char[][] p = {{0, 0, 0, 1, 0, 0, 0}, 
					  {0, 0, 1, 1, 1, 0, 0}, 
					  {0, 1, 1, 1, 1, 1, 0}};
		System.out.println(maximalRectangle(p));
	}
}
