package dfs;

import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindBottomLeftTreeValue_513 {
	
	public int findBottomLeftValue(TreeNode root) {
		return findBottomLeftValue(root, 1, new int[] {0, 0});
	}

	public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
		if (res[1] < depth) {
			res[0] = root.val;
			res[1] = depth;
		}
		if (root.left != null)
			findBottomLeftValue(root.left, depth + 1, res);
		if (root.right != null)
			findBottomLeftValue(root.right, depth + 1, res);
		return res[0];
	}
	
	/* BFS */
	
	public int findBottomLeftValue1(TreeNode root) {
	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.add(root);
	    while (!queue.isEmpty()) {
	        root = queue.poll();
	        if (root.right != null)
	            queue.add(root.right);
	        if (root.left != null)
	            queue.add(root.left);
	    }
	    return root.val;
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
