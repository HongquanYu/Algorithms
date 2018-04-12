package bloomberg;

import binarySearchTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class InsertIntoBST {
	public TreeNode insert(TreeNode root, int val) {
	    if (root == null)
	    		return new TreeNode(val);
	    else if (root.val < val)
	    		root.right = insert(root.right, val);
	    else if (root.val > val)
	    		root.left = insert(root.left, val);
	    
	    	return root;
	}
}
