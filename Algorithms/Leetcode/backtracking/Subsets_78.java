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
public class Subsets_78 {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);		// 确保每一个子集的元素都是有序的！
		backtrack(res, new ArrayList<>(), nums, 0);
		return res;
	}
	
	// 添加一个元素到本集合并保存，再利用此集合进行下一次的迭代！
	private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
		res.add(new ArrayList<>(tempList));			// 先把之前的结果保存到结果，我们要进行依次遍历剩下的元素了
		
		for (int i = start; i < nums.length; i++) {	// 当前method对每个节点都遍历一遍
			tempList.add(nums[i]);					// 对于当前结果进行加一个recursively计算下一个结果
			backtrack(res, tempList, nums, i + 1);	// 下一次迭代的时候已经剔除了当前元素
			tempList.remove(tempList.size() - 1);	// 移除当前元素，以供本次方法进行下一个元素的尝试
		}
	}
}
