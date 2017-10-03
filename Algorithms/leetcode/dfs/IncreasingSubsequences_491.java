package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences_491 {
	public List<List<Integer>> findSubsequences(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		helper(new LinkedList<Integer>(), 0, nums, res);
		return res;
	}

	private void helper(LinkedList<Integer> newlist, int index, int[] nums, List<List<Integer>> res) {
		if (newlist.size() > 1)			// a valid increasing subsequence, add it into solution
			res.add(new LinkedList<Integer>(newlist));
		
		Set<Integer> used = new HashSet<>();
		
		for (int i = index; i < nums.length; i++) {
			if (used.contains(nums[i]))
				continue;
			if (newlist.size() == 0 || nums[i] >= newlist.peekLast()) {
				used.add(nums[i]);
				newlist.add(nums[i]);
				helper(newlist, i + 1, nums, res);
				newlist.remove(newlist.size() - 1);
			}
		}
	}
	
	/* Backtrack */
	
	public List<List<Integer>> findSubsequences2(int[] nums) {
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		List<Integer> holder = new ArrayList<Integer>();
		findSequence(res, holder, 0, nums);
		List<List<Integer>> result = new ArrayList<>(res);
		return result;
	}

	public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
		if (holder.size() >= 2) {
			res.add(new ArrayList<>(holder));
		}
		for (int i = index; i < nums.length; i++) {
			if (holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
				holder.add(nums[i]);
				findSequence(res, holder, i + 1, nums);
				holder.remove(holder.size() - 1);
			}
		}
	}
}
