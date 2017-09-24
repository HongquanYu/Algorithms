package binarytree;

/**
 * 538. Convert BST to Greater Tree
 * 
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that
 * every key of the original BST is changed to the original key plus sum of all
 * keys greater than the original key in BST.
 * 
 * Example:
 * 
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *            20    13
 */

public class ConvertBSTtoGreaterTree_538 {
	
	/** Since this is a BST, we can do a reverse inorder traversal to traverse
	 * the nodes of the tree in descending order. In the process, we keep track
	 * of the running sum of all nodes which we have traversed thus far. */
	
	private int sum = 0;

	public TreeNode convertBST(TreeNode root) {
		convert(root);
		return root;
	}

	public void convert(TreeNode cur) {
		if (cur == null)
			return;
		
		convert(cur.right);
		
		cur.val += sum;
		sum = cur.val;		// sum accumulates all the beigger node values 
		
		convert(cur.left);
	}
}
