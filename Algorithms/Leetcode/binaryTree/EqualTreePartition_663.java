package binaryTree;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Mar 5, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class EqualTreePartition_663 {
	Stack<Integer> seen;
	
	public boolean checkEqualTree(TreeNode root) {
		seen = new Stack<>();
		int total = sum(root);
		seen.pop();
		if (total % 2 == 0) {
			for (int s : seen) {
				if (s == total / 2)
					return true;
			}
		}
		return false;
	}

	public int sum(TreeNode node) {
		if (node == null)
			return 0;
		seen.push(sum(node.left) + sum(node.right) + node.val);
		return seen.peek();
	}
}
