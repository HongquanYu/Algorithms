package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BalancedBinaryTree_110 {
	
	/* O(N2) */
	
	public boolean isBalanced(TreeNode root) {
	    if (root == null) return true;
	    return Math.abs(Height(root.left) - Height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
	}

	public int Height(TreeNode root) {
	    if (root == null)
	        return 0;
	    return Math.max(Height(root.left), Height(root.right)) + 1;
	}
	
	/* O(N) */
	
	public boolean isBalanced2(TreeNode root) {
	    if (root == null) return true;
	    return dfsHeight(root) != -1;
	}

	public int dfsHeight(TreeNode root) {
	    if (root == null)
	        return 0;
	    int leftHeight = dfsHeight(root.left);
	    if (leftHeight == -1) return -1;
	    int rightHeight = dfsHeight(root.right);
	    if (rightHeight == -1) return -1;
	    if (Math.abs(leftHeight - rightHeight) > 1)
	        return -1;
	    return Math.max(dfsHeight(root.left), dfsHeight(root.right)) + 1;
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
