package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree_226 {
	
    public TreeNode invertTreeRrcursion(TreeNode root) {
		if (root == null) 	return null;
        
        final TreeNode left = root.left;
        final TreeNode right = root.right;
        
		root.left = invertTree(right);		// 当前的左节点换成 当前右节点的旋转
		root.right = invertTree(left);		// 当前的右节点换成 当前左节点的旋转
		
		return root;
	}
	
	/** ---------------------my solution - level traversal------------------------  */
	
	public TreeNode invertTree(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (root == null)
			return null;
		q.add(root);

		while (!q.isEmpty()) {
			
			// 交换左右节点
			TreeNode tmp = q.poll();
			TreeNode left = tmp.left;
			tmp.left = tmp.right;
			tmp.right = left;

			if (tmp.left != null)
				q.add(tmp.left);
			
			if (tmp.right != null)
				q.add(tmp.right);
		}

		return root;
	}
}
