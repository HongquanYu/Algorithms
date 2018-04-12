package bfs;

import java.util.LinkedList;
import java.util.Queue;

import binaryTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumDepthOfBinaryTree_111 {
	
	/** level order */
	
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int depth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left == null && node.right == null)		// 到底了
					return depth;
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
			depth++;
		}
		return depth;
	}
}
