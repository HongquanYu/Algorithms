package binaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** 404. Sum of Left Leaves
 * 
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
 * 		 3
 *  		/ \  
 * 	   9   20
 * 		   / \
 * 		 15   7
 * 
 * There are two left leaves in the binary tree, with values 9 and 15
 * respectively. Return 24. */

public class SumOfLeftLeaves_404 {
	
	/** Recursion：
	 * 对每一个节点
	 * 1， 若左子树不为空
	 * 		- 检查其左节点是不是叶子节点，若是就累加到结果
	 * 		- 若不是则recursively 对左节点进行递归。
	 * 2， 对其右子树进行递归 */
	
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) 	return 0;
		int count = 0;
		
		// Only deal with left subtree, recursive to right subtree
		if (root.left != null) {
			if (root.left.left == null && root.left.right == null)	// left is a leaf
				count += root.left.val;
			else
				count += sumOfLeftLeaves(root.left);					// non-leaf, recursively call
		}
		count += sumOfLeftLeaves(root.right);	// recursively call right subtree

		return count;
	}
	
	/** 用一个 stack 来追踪每个非叶子节点，非叶子节点就入栈，遍历完栈的所有节点
	 * 对每一个出站的节点，我们都检查其左孩子是不是叶子节点，若是的话就加入结果 */
	
	public int sumOfLeftLeaves2(TreeNode root) {
		if (root == null) 	return 0;
		
		int sum = 0;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.empty()) {
			TreeNode node = stack.pop();
			
			if (node.left != null) {		// left sub-node
				if (node.left.left == null && node.left.right == null)	// leaf
					sum += node.left.val;
				else						// non-leaf, push to stack
					stack.push(node.left);
			}
			if (node.right != null) {	// right sub-node
				if (node.right.left != null || node.right.right != null)	// non-leaf, push to stack; leaf, skip
					stack.push(node.right);
			}
		}
		return sum;
	}
	
	/** BFS 解法是靠队列 Queue 来实现的
	 * 相当于进行层次遍历，只有叶子节点是某节点的左节点才会被加入到结果
	 * 对于每个节点，检查其左孩子是不是叶子，是就加入结果。 */
	
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

			if (curr.left != null)
				queue.offer(curr.left);
			
			if (curr.right != null)
				queue.offer(curr.right);
		}
		
		return sum;
	}
}
