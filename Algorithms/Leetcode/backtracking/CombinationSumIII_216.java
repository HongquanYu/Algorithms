package backtracking;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CombinationSumIII_216 {
	
	public List<List<Integer>> bfsSum3(int k, int n) {
		List<List<Integer>> ans = new ArrayList<>();
		bfs(ans, new ArrayList<Integer>(), k, 1, n);
		return ans;
	}

	private void bfs(List<List<Integer>> ans, List<Integer> temp, int k, int start, int n) {
		if (temp.size() == k && n == 0) {	// 当前是一个解
			List<Integer> li = new ArrayList<>(temp);
			ans.add(li);
			return;
		}
		for (int i = start; i <= 9; i++) {
			temp.add(i);
			bfs(ans, temp, k, i + 1, n - i);	// 进入下一层进行递归寻找
			temp.remove(temp.size() - 1);
		}
	}
}
