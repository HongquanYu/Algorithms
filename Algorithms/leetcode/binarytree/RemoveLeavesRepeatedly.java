package binarytree;

import java.util.ArrayList;
import java.util.List;

public class RemoveLeavesRepeatedly {
	
	// -------------------------------- Recursion - DFS - ----------------------------------
	
	public List<List<Integer>> findLeaves(TreeNode root) {
		
		List<List<Integer>> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	private int helper(TreeNode node, List<List<Integer>> res) {
		if (null == node)	return -1;
		
		int level = 1 + Math.max(helper(node.left, res), helper(node.right, res));	// get height of tree
		
		if (res.size() < level + 1)		// there should be height + 1 leaves collection
			res.add(new ArrayList<>());
		
		res.get(level).add(node.val);
		
		return level;
	}
	
	/** The essential of problem is not to find the leaves, but group leaves of
	 * same level together and also to cut the tree. This is the exact role
	 * backtracking plays. The helper function returns the level which is the
	 * distance from its furthest subtree leaf to root, which helps to identify
	 * which group the root belongs to */
	
	public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        findLeavesHelper(list, root);
        return list;
    }
    
	// return the level of root
	private int findLeavesHelper(List<List<Integer>> list, TreeNode root) {
		if (root == null)
			return -1;
		
		int leftLevel = findLeavesHelper(list, root.left);
		int rightLevel = findLeavesHelper(list, root.right);
		int level = Math.max(leftLevel, rightLevel) + 1;
		
		if (list.size() == level)
			list.add(new ArrayList<>());
		
		list.get(level).add(root.val);
		root.left = root.right = null;
		return level;
	}
}
