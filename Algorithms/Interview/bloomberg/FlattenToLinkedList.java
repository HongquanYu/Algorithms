package bloomberg;

import java.util.Stack;

import binaryTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Feb 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FlattenToLinkedList {
	
	/** Flatten 一颗二叉树 */
	
	public void flatten(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		if (root == null) return;
		
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode poped = stack.pop();

			if (poped.right != null)
				stack.push(poped.right);
			if (poped.left != null)
				stack.push(poped.left);

			poped.left = null;				// 当前节点指向下一个，左节点设空
			if (!stack.isEmpty())
				poped.right = stack.peek();
		}
	}
	
	/** Flatten 包含有 down 链接的链表 */
	
	public void flatten(Node d) {
		Stack<Node> stack = new Stack<>();
		
		if (d == null)	return;
		stack.push(d);
		
		while (!stack.isEmpty()) {
			Node poped = stack.pop();
			
			if (poped.next != null)
				stack.push(poped.next);
			if (poped.down != null)
				stack.push(poped.down);
			
			poped.down = null;
			if (!stack.isEmpty())
				poped.next = stack.peek();
		}
	}
	
	private class Node {
		int data;
		Node next, down;
		
		Node(int d, Node next, Node down) {
			this.data = d;
			this.next = next;
			this.down = down;
		}
	}
}	
