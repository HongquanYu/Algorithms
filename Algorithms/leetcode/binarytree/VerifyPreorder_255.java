package binarytree;

import java.util.Stack;

/** 255. Verify Preorder Sequence in Binary Search Tree
 * 
 * Given an array of numbers, verify whether it is the correct preorder
 * traversal sequence of a binary search tree.
 * 
 * You may assume each number in the sequence is unique.
 * 
 * Follow up: Could you do it using only constant space complexity? */

public class VerifyPreorder_255 {
	
	public boolean verifyPreorder(int[] preorder) {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> inorder = new Stack<>();

		for (int v : preorder) {
			if (!inorder.isEmpty() && v < inorder.peek())
				return false;
			while (!stack.isEmpty() && v > stack.peek()) {
				inorder.push(stack.pop());
			}
			stack.push(v);
		}
		
		return true;
	}
	

	/**
	 * Simulating the traversal, use a stack to tracking node values of which we're still in the left subtree.
	 * If the next number is smaller than the last stack value, then we're still in the left subtree
	 * of all stack nodes, so just push the new one onto the stack. But before
	 * that, pop all smaller ancestor values, as we must now be in their right
	 * subtrees (or even further, in the right subtree of an ancestor). Also,
	 * use the popped values as a lower bound, since being in their right
	 * subtree means we must never come across a smaller number anymore.
	 */

	public boolean verifyPreorder2(int[] preorder) {
		
		int low = Integer.MIN_VALUE;
		Stack<Integer> path = new Stack<>();
		
		for (int p : preorder) {
			if (p < low)
				return false;
			while (!path.empty() && p > path.peek())	// encounter a 
				low = path.pop();
			path.push(p);
		}
		
		return true;
	}
}
