package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** 404. Sum of Left Leaves
 * 
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
 * 		 3
 *  	/ \  
 * 	   9   20
 * 		   / \
 * 		 15   7
 * 
 * There are two left leaves in the binary tree, with values 9 and 15
 * respectively. Return 24. */

public class SumOfLeftLeaves_404 {
	
	// ----------------------------  Recursion   ----------------------------------
	
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null)
			return 0;
		
		int count = 0;
		if (root.left != null) {	// Only deal with left subtree, recursive to right subtree
			if (root.left.left == null && root.left.right == null)	// left is a leaf
				count += root.left.val;
			else
				count += sumOfLeftLeaves(root.left);	// non-leaf, recursively call
		}
		count += sumOfLeftLeaves(root.right);	// recursively call right subtree

		return count;
	}
	
	// ----------------------------  Iterative   ----------------------------------
	
	public int sumOfLeftLeaves2(TreeNode root) {
		if (root == null) 	return 0;
		
		int sum = 0;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.empty()) {
			
			TreeNode node = stack.pop();
			
			if (node.left != null) {	// left sub-node
				if (node.left.left == null && node.left.right == null)	// leaf
					sum += node.left.val;
				else	// non-leaf, push to stack
					stack.push(node.left);
			}
			if (node.right != null) {	// right sub-node
				if (node.right.left != null || node.right.right != null)	// non-leaf, push to stack; leaf, skip
					stack.push(node.right);
			}
		}
		return sum;
	}
	
	// ----------------------------  BFS   ----------------------------------
	
	public int sumOfLeftLeaves3(TreeNode root) {
		if (root == null || root.left == null && root.right == null)	// null or only one node
			return 0;

		int sum = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			
			// left node is leaf. For every node, skip right sub-node
			if (curr.left != null && curr.left.left == null && curr.left.right == null)
				sum += curr.left.val;
			
			// left node is not leaf
			if (curr.left != null)
				queue.offer(curr.left);
			
			// add right node to stack
			if (curr.right != null)
				queue.offer(curr.right);
		}
		return sum;
	}
}
