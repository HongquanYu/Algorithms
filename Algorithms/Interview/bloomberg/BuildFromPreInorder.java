package bloomberg;

import binaryTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BuildFromPreInorder {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return build(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode build(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd)	// boundary check
			return null;

		TreeNode root = new TreeNode(preorder[preStart]);		// pre-order[0] is root
		int inRoot = 0;										// Index of current root in in-order

		for (int i = inStart; i <= inEnd; i++) 				// find root in in-order
			if (inorder[i] == root.val)
				inRoot = i;

		root.left = build(preStart + 1, inStart, inRoot - 1, preorder, inorder);
		root.right = build(preStart + (inRoot - inStart) + 1, inRoot + 1, inEnd, preorder, inorder);

		return root;
	}
}
