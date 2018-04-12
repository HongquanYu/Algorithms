package array;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Mar 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LargestRectangleInHistogram_84 {
	
	/** 1, Brute Force Solution takes quadratic time!
	 *  2, Divide and Conquer with Segment Tree takes o(NlgN)
	 *  3, Divide and Conquer without Segment Tree takes o(NlgN)
	 *  思想就是：最大区间要么出现在数组左边，要么右边，要么从中间开始！ */
	
	public int largestRectangleAreaDQ(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		return largestArea(A, 0, A.length - 1);
	}

	private int largestArea(int[] A, int l, int r) {
		if (l == r) 	return A[l];
		
		int mid = l + (r - l) / 2;
		int area = Math.max(largestArea(A, l, mid), largestArea(A, mid + 1, r));
		area = Math.max(area, maxFullRange(A, l, mid, r));
		return area;
	}
	
	private int maxFullRange(int[] A, int l, int mid, int r) {
		int i = mid, j = mid + 1;
		int area = 0, h = Math.min(A[i], A[j]);
		
		while (i >= l && j <= r) {
			h = Math.min(h, Math.min(A[i], A[j]));
			area = Math.max(area, (j - i + 1) * h);
			
			if 		(i == l) 	++j;		// 左边到头了，移动右边
			else if (j == r) 	--i;		// 右边到头了，移动左边
			else {						// 移动大的那一边
				if (A[i - 1] > A[j + 1]) 	--i;
				else 						++j;
			}
		}
		return area;
	}
	
	/** O(n)的解法。思想是：
	 *  对于每一个bar，我们以它为最低高度计算 rectangle 的面积。我们就需要知道左右边比他更小的
	 *  bar 的高度在哪里。过程中追踪最大面积，遍历完成返回。
	 *  用一个栈来做：
	 *  1，保持栈为递增，当遇到一个小于栈顶的元素，我们就出栈直到遇到栈顶元素不会比当前元素大
	 *  每一次出栈都进行一次面积计算并追踪最大值。算的面积是从栈顶元素为高，宽是当前元素和栈顶的距离 - 1
	 *  One Tricky Part：当前没有那么多。遍历比数组元素多一个，最后一个将高设为0！ */
	
	public int largestRectangleArea1(int[] height) {
		int N = height.length;
		Stack<Integer> index = new Stack<>();
		int maxArea = 0;
		
		for (int i = 0; i <= N; i++) {
			int h = (i == N ? 0 : height[i]);
			if (index.isEmpty() || h >= height[index.peek()]) {
				index.push(i);
			} else {
				int area = height[index.pop()] * (index.isEmpty() ? i : i - 1 - index.peek());
				maxArea = Math.max(maxArea, area);
				i--;
			}
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		int[] tmp = new int[] {2, 1, 5, 6, 2, 3};
		
		LargestRectangleInHistogram_84 l = new LargestRectangleInHistogram_84();
		
		System.out.println(l.largestRectangleArea1(tmp));
	}
}
