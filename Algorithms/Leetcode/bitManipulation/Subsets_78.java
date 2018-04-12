package bitManipulation;

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
		int subsets = 1 << S.length;		// 2^n
		List<List<Integer>> res = new ArrayList<List<Integer>>(subsets);
		
		for (int i = 0; i < subsets; i++) {
			List<Integer> set = new LinkedList<Integer>();
			
			// Find a subset
			for (int j = 0; j < S.length; j++)
				if ((i & (1 << j)) != 0)		// If current element is set, add into solution
					set.add(S[j]);
			
			res.add(set);	// Add subset into solution
		}
		
		return res;
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
