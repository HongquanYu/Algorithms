package binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class InvertTree_226 {
	/**
	 * ---------------------my solution------------------------- use level
	 * traversal
	 */
	public TreeNode invertTree(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (root == null)
			return null;
		q.add(root);

		while (!q.isEmpty()) {
			TreeNode popedItem = q.poll();
			TreeNode left = popedItem.left;
			popedItem.left = popedItem.right;
			popedItem.right = left;

			if (popedItem.left != null)
				q.add(popedItem.left);
			
			if (popedItem.right != null)
				q.add(popedItem.right);
		}

		return root;
	}
}
