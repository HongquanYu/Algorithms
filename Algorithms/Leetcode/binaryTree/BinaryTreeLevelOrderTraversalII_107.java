package binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 107. Binary Tree Level Order Traversal II
 * 
 * Given a binary tree, return the bottom-up level order traversal of its nodes'
 * values. (ie, from left to right, level by level from leaf to root). */

public class BinaryTreeLevelOrderTraversalII_107 {
	
	// ----------------------------  Iterative - DFS  ----------------------------------
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> res = new LinkedList<List<Integer>>();

		if (root == null)
			return res;

		queue.offer(root);
		
		while (!queue.isEmpty()) {
			
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			
			for (int i = 0; i < levelNum; i++) {
				if (queue.peek().left != null)
					queue.offer(queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				subList.add(queue.poll().val);
			}
			
			res.add(0, subList);	// add from head
		}
		
		return res;
	}
	
	// ----------------------------  Recursion - BFS   ----------------------------------
	
	public List<List<Integer>> levelOrderBottom2(TreeNode root) {
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		levelMaker(res, root, 0);
		return res;
	}

	public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
		if (root == null)
			return;
		if (level >= list.size())
			list.add(0, new LinkedList<Integer>());	// add from head
		
		levelMaker(list, root.left, level + 1);
		levelMaker(list, root.right, level + 1);
		list.get(list.size() - level - 1).add(root.val);
	}
}
