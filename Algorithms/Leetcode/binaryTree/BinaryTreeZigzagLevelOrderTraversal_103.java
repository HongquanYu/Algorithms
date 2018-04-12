package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 103. Binary Tree Zigzag Level Order Traversal
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between). */

public class BinaryTreeZigzagLevelOrderTraversal_103 {
	
	/** Level order resution iteratively */
	
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)	return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        
		while (!queue.isEmpty()) {

			int size = queue.size();
			LinkedList<Integer> list = new LinkedList<>();

			while (size-- > 0) {
				TreeNode t = queue.poll();
				if (res.size() % 2 == 1)
					list.addFirst(t.val);
				else
					list.add(t.val);

				if (t.left != null)
					queue.offer(t.left);
				if (t.right != null)
					queue.offer(t.right);
			}

			res.add(list);
		}
        
        return res;
    }
    
	// ---------------------------- DFS - Recursion ----------------------------------

	public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		travel(root, res, 0);
		return res;
	}

	private void travel(TreeNode curr, List<List<Integer>> res, int level) {
		if (curr == null)
			return;

		if (res.size() <= level)
			res.add(new LinkedList<>());

		if (level % 2 == 0)
			res.get(level).add(curr.val);
		else
			res.get(level).add(0, curr.val);

		travel(curr.left, res, level + 1);
		travel(curr.right, res, level + 1);
	}
}
