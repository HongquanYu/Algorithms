package dp;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Mar 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LargestPlusSign_764 {
	
	/** 基本思想是：N^2
	 * 对于每一个 grid cell, 我们尽量向4个方向extend，这个cell的值就是最小的那个值
	 * l, r, u, d 是 4 个方向上的长度，初始化为 0 是因为在 matrix 的左边没有元素，长度为 0。
	 * u 也是相同的道理。对于多一个索引 k，k 是当作 column 的索引来做计算的长度，由于是正方形，
	 * 所以它也可以用作来计算 d 和 r。
	 * l, r, u, d 四个变量使得时间复杂度降低了一个线性至N^2。他们分别追踪每个元素四个方向上的
	 * 连续 ‘1’ 的长度。
	 * 疑问：为什么当开始的时候如 i = 0, j = 0 的时候我们还没有计算右边 r 的长度我们就可以得出
	 * grid[i][j] 的答案？ 因为左边为0，而我们要的是最小值，所以可以直接得出0。当我们算到第一行
	 * 中间时候，左边的 l 是随着遍历一起计算的；而右边的r是在索引 k 的帮助下也进行了 N/2 遍计算，
	 * 即使所有为 ‘1’，每一个 cell 能得到的最大值也只能是 N/2。对于 u, d 是同样的道理 */
	
	public static int orderOfLargestPlusSign(int N, int[][] mines) {
		int[][] grid = new int[N][N];

		for (int i = 0; i < N; i++)
			Arrays.fill(grid[i], N);

		for (int[] m : mines)
			grid[m[0]][m[1]] = 0;
		
		// 对于每一行，同时从开始和结束进行计算
		for (int i = 0; i < N; i++) {	
			for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
				grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));
				grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));
				grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));
				grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));
			}
		}

		int res = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				res = Math.max(res, grid[i][j]);

		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(orderOfLargestPlusSign(5, new int[][] {{4, 2}}));
	}
}
