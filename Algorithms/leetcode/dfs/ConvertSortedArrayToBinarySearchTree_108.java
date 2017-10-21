package dfs;

import dfs.BalancedBinaryTree_110.TreeNode;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ConvertSortedArrayToBinarySearchTree_108 {
	public TreeNode sortedArrayToBST(int[] num) {
	    if (num.length == 0) {
	        return null;
	    }
	    TreeNode head = helper(num, 0, num.length - 1);
	    return head;
	}

	public TreeNode helper(int[] num, int low, int high) {
	    if (low > high) { // Done
	        return null;
	    }
	    int mid = (low + high) / 2;
	    TreeNode node = new TreeNode(num[mid]);
	    node.left = helper(num, low, mid - 1);
	    node.right = helper(num, mid + 1, high);
	    return node;
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
