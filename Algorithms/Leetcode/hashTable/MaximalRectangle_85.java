package hashTable;

import java.util.Stack;

public class MaximalRectangle_85 {
	
	/* You can maintain a row length of Integer array H recorded its height of '1’s, 
	 * scan and update row by row to find out the largest rectangle of each row.
	 * 
	 * For each row, if matrix[row][i] == ‘1’. H[i] +=1, or reset the H[i] to zero. */
	
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		
		int col = matrix[0].length, row = matrix.length;
		int[] height = new int[col + 1];
		height[col] = 0;
		int max = 0;


		for (int r = 0; r < row; r++) {
			Stack<Integer> stack = new Stack<Integer>();		// 记录的是每一行当前列之前的列数，倒序
			for (int c = 0; c < col + 1; c++) {
				if (c < col) {		// 记录每一列的高度，即1的个数
					if (matrix[r][c] == '1') 	height[c] += 1;		// 记录列中连续的1的高度
					else 						height[c] = 0;		// 任何时候出现个0，都reset为0
				}

				while (!stack.isEmpty() &&  height[stack.peek()] > height[c]) {	// 遍历栈元素知道栈顶元素比height高
					int h = height[stack.pop()];
					int w = stack.isEmpty() ? c : (c - stack.peek() - 1);		// 减1是因为不包括当前列
					max = Math.max(h * w, max);		// 更新面积
				}
				stack.push(c);
			}
		}
		
		return max;
	}
}
