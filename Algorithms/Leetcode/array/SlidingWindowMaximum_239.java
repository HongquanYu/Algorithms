package array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/** @author: Hongquan Yu
 *  @date: Mar 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class SlidingWindowMaximum_239 {
	
	/** 我们维持一个两端可以进出队列，在 java 里用 deque。
	 * 在这个deque里，我们始终元素维持在 k 以内，所以得将多于 k 的元素从队列头移除掉。
	 * 然后对于队列尾（后入）的元素，我们移除比当前元素还小的元素，因为他们永远不可能成为最大
	 * ，可以放心的移除。最后加入当前元素  */
	
	public int[] maxSlidingWindow(int[] A, int k) {
		if (A == null || k <= 0)
			return new int[0];
		
		int[] res = new int[A.length - k + 1];
		Deque<Integer> q = new ArrayDeque<>();	// 存的是索引
		
		for (int i = 0, ptr = 0; i < A.length; i++) {
			while (!q.isEmpty() && q.peek() < i - k + 1)		// remove numbers out of range k
				q.poll();
			System.out.print("F: " + q.toString() + "\t\t");
			
			while (!q.isEmpty() && A[q.peekLast()] < A[i]) 	// remove smaller numbers in k range as they are useless
				q.pollLast();
			System.out.print("S: " + q.toString() + "\t\t");
			
			q.offer(i);
			System.out.println(q.toString());
			if (i >= k - 1)		// 当前索引 i 是有效的
				res[ptr++] = A[q.peek()];
		}
		return res;
	}
	
	/** 两个遍历，就能做到 */
	
	public int[] slidingWindowMax2(final int[] in, final int w) {
		final int N = in.length;
		final int[] maxLeft = new int[N];
		final int[] maxRight = new int[N];

		maxLeft[0] = in[0];
		maxRight[N - 1] = in[N - 1];

		for (int i = 1; i < N; i++) {
			maxLeft[i] = (i % w == 0) ? in[i] : Math.max(maxLeft[i - 1], in[i]);

			final int j = N - i - 1;
			maxRight[j] = (j % w == 0) ? in[j] : Math.max(maxRight[j + 1], in[j]);
		}

		final int[] finalMax = new int[N - w + 1];
		for (int i = 0, j = 0; i + w <= N; i++)
			finalMax[j++] = Math.max(maxRight[i], maxLeft[i + w - 1]);

		return finalMax;
	}
	
	public static void main(String[] args) {
		int[] tmp = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
		SlidingWindowMaximum_239 s = new SlidingWindowMaximum_239();
		
		System.out.println(Arrays.toString(s.maxSlidingWindow(tmp, 3)));
	}
}
