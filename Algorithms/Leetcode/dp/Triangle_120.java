package dp;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Triangle_120 {
	
	/** dp[k][i] = min( dp[k+1][i], dp[k+1][i+1]) + triangle[k][i];
	 * For the kth level:
	 * dp[i] = min( dp[i], dp[i+1]) + triangle[k][i]; */
	
	public int minimumTotal(List<List<Integer>> triangle) {
		Deque<Integer> queue = new LinkedList<Integer>();
		int count = triangle.size();
		queue.add(triangle.get(0).get(0));
		
		for (int i = 1; i < count; i++) {
			List<Integer> list = triangle.get(i);
			for (int j = 0; j <= i; j++) {
				int min = 0;
				if (j == 0) 			min = list.get(0) + queue.peekFirst();
				else if (j == i) 	min = list.get(j) + queue.pollFirst();
				else 				min = Math.min(queue.pollFirst(), queue.peekFirst()) + list.get(j);
				queue.addLast(min);
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++)
			result = Math.min(result, queue.pollFirst());
		
		return result;
	}
	
	/** Concise Version，从下至上修改每一个位置到底的最小路径，最后返回第一行第一个 */
	
	public int minimumTotal2(List<List<Integer>> triangle) {
		if (triangle.size() == 0)
			return 0;

		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				List<Integer> nextRow = triangle.get(i + 1);
				int sum = Math.min(nextRow.get(j), nextRow.get(j + 1)) + triangle.get(i).get(j);
				triangle.get(i).set(j, sum);
			}
		}
		return triangle.get(0).get(0);
	}
}
