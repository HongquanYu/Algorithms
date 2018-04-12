package binarySearch;

import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class KthSmallestElementInASortedMatrix_378 {
	
	/* 思想就是： 当我们每一次迭代找到比中间数等于或者小于的个数，要是这个个数比k小，那么右边界
	 * 向中间移，否则左边界向中间移。直到左右边界重合，我们就算是找到了第k个元素。
	 * 这道题是典型的space binary search */
	
	public int kthSmallest(int[][] matrix, int k) {
		int r = matrix.length, c = matrix[0].length;
		
		// floor 和 ceiling 存的是元素值，
		int floor = matrix[0][0];
		int ceiling = matrix[r - 1][c - 1] + 1;		// [lo, hi)
		
		while (floor < ceiling) {	//最后跳出循环是 floor == ceiling == k
			
			int mid = floor + (ceiling - floor) / 2;
			int count = 0, j = c - 1;
			
			// Count number of elements which are <= mid
			for (int i = 0; i < r; i++) {		// 为什么可以复用j，因为下一排的jth位置肯定比 mid 大
				while (j >= 0 && matrix[i][j] > mid)			// reach elements which <= mid in each row
					j--;
				count += (j + 1);
			}
			
			if (count < k) 		floor = mid + 1;		// 这里比较的是 比 k 小的元素个数和k的相对大小
			else 				ceiling = mid;
		}
		
		return floor;	// 返回第 K 个元素
	}
	
	/* Heap */
	
	public int kthSmallest2(int[][] matrix, int k) {
		int n = matrix.length;
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
		
		for (int j = 0; j <= n - 1; j++)
			pq.offer(new Tuple(0, j, matrix[0][j]));
		for (int i = 0; i < k - 1; i++) {
			Tuple t = pq.poll();
			if (t.x == n - 1)
				continue;
			pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
		}
		return pq.poll().val;
	}

	class Tuple implements Comparable<Tuple> {
		int x, y, val;

		public Tuple(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Tuple that) {
			return this.val - that.val;
		}
	}
}
