package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SubsetsII_90 {
	
	/** Backtracking method:
	 * 对于skip duplicates的filter做一点解释：
	 * 例如我们用 [1, 2, 2] 的结果来解释
	 * 当我们当前 recursive 元素是第一个2，那么我们加入2并迭代至下一层，由于在下一层中第2个2虽然也符合
	 * nums[i] == nums[i - 1]，但是他却不满足i > start，因为其i == start，所以能够成功的加入[1, 2, 2]
	 * 这个解！
	 * 但是算法是怎么避免加入两个 [2] 和 [1, 2] 的呢？ 
	 * 当算法call当前method（start指向第一个2）加入2使现在的集合是[1, 2]的时候，当前method要进行回溯删去当前2，
	 * 集合又变回[1]。进行下一个2的遍历的时候，我们看到了其满足了i > start && nums[i] == nums[i - 1]的筛选条件
	 * 所以我们继续，以避免了[1，2]加入2次的可能。
	 * 同样对于[2]，是第一层backtrack遍历到第2个2的时候已经符合了筛选条件，所以我们跳过重复添加[2]这个解！
	 * 但是这个筛选条件又不影响[1, 2]添加成[1, 2, 2]这样的结果！ */
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(res, new ArrayList<>(), nums, 0);
		return res;
	}

	private void backtrack(List<List<Integer>> res, List<Integer> sub, int[] nums, int start) {
		res.add(new ArrayList<>(sub));
		
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1])		// filter: skip duplicates
				continue;
			sub.add(nums[i]);
			backtrack(res, sub, nums, i + 1);
			sub.remove(sub.size() - 1);
		}
	}
	
	/**  */
	
	public List<List<Integer>> subsetsWithDup2(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> empty = new ArrayList<Integer>();
		result.add(empty);
		Arrays.sort(num);

		for (int i = 0; i < num.length; i++) {
			int dupCount = 0;
			while (((i + 1) < num.length) && num[i + 1] == num[i]) {
				dupCount++;
				i++;
			}
			int prevNum = result.size();
			for (int j = 0; j < prevNum; j++) {
				List<Integer> element = new ArrayList<Integer>(result.get(j));
				for (int t = 0; t <= dupCount; t++) {
					element.add(num[i]);
					result.add(new ArrayList<Integer>(element));
				}
			}
		}

		return result;
	}
}
