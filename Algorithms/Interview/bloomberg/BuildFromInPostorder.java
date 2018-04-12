package bloomberg;

import java.util.HashMap;

import binaryTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BuildFromInPostorder {
	public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null || inorder.length != postorder.length)
			return null;

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; ++i) // in-order into hashmap
			map.put(inorder[i], i);
		
		return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
	}

	private TreeNode helper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, HashMap<Integer, Integer> map) {
		if (ps > pe || is > ie) 	// boundary check
			return null;

		TreeNode root = new TreeNode(postorder[pe]); 	// get root
		int inRoot = map.get(postorder[pe]); 				// get index of root node in in-order

		root.left = helper(inorder, is, inRoot - 1, postorder, ps, ps + inRoot - is - 1, map);
		root.right = helper(inorder, inRoot + 1, ie, postorder, ps + inRoot - is, pe - 1, map);

		return root;

	}
}
