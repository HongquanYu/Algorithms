package bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Subsets_78 {
	
	public List<List<Integer>> subsets(int[] S) {
		Arrays.sort(S);
		int totalNumber = 1 << S.length;
		List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
		for (int i = 0; i < totalNumber; i++) {
			List<Integer> set = new LinkedList<Integer>();
			for (int j = 0; j < S.length; j++) {
				if ((i & (1 << j)) != 0) {
					set.add(S[j]);
				}
			}
			collection.add(set);
		}
		return collection;
	}
	
	/* Backtrack */
	
	public List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums,
			int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
