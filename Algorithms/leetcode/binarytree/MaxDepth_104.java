package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxDepth_104 {
	/**
	 * 104. Given a binary tree, find its maximum depth.
	 * 
	 * The maximum depth is the number of nodes along the longest path from the
	 * root node down to the farthest leaf node.
	 * 
	 */

	// ---------------------  Recursion-------------------------

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	// --------------------- Iterative BFS - Level traversal-------------------------

	public int maxDepth2(TreeNode root) {
		if (root == null)
			return 0;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int count = 0;

		while (!queue.isEmpty()) {
			int levelNodeNumber = queue.size();
			while (levelNodeNumber-- > 0) {
				TreeNode node = queue.poll();
				if (node.left != null)
					queue.offer(node.left);

				if (node.right != null)
					queue.offer(node.right);
			}
			count++;
		}
		return count;
	}

	// --------------------- Iterative DFS-------------------------

	public int maxDepth3(TreeNode root) {
		if (root == null)
			return 0;

		Stack<TreeNode> stack = new Stack<>();	// manage nodes
		Stack<Integer> value = new Stack<>();	// record current heights
		
		stack.push(root);
		value.push(1);
		int max = 0;
		
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			int temp = value.pop();
			max = Math.max(temp, max);
			
			if (node.left != null) {
				stack.push(node.left);
				value.push(temp + 1);	// same level, same height
			}
			if (node.right != null) {
				stack.push(node.right);
				value.push(temp + 1);	// same level, same height
			}
		}
		return max;
	}
}
