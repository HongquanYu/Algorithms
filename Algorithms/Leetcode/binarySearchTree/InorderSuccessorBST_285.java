package binarySearchTree;

import java.util.Stack;

/** 285. Inorder Successor in BST
 * 
 * Given a binary search tree and a node in it, find the in-order successor of
 * that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null. */

public class InorderSuccessorBST_285 {
	
	/** 进行中序遍历，弹出栈的时候要有一些 */
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		
		Stack<TreeNode> stack = new Stack<>();
		TreeNode successor = null;
		
		if (root == null || p == null)
			return successor;

		TreeNode cur = root;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}

			cur = stack.pop();
			// 当前弹出node是 p node，那么就找右子树的最左子节点
			if (cur == p) {
				TreeNode t = null;
				if (cur.right != null) {		// 有右子树时，找到其最左子节点
					t = cur.right;
					while (t.left != null)
						t = t.left;
					successor = t;
				} else {			// 没有右子树，那么就从栈里找到下一个节点
					successor = (stack.size() != 0) ? stack.peek() : null;
					break;
				}
			}

			cur = cur.right;
		}

		return successor;
	}
    
	/** BST 当前接待你的下一个节点就是当前节点的右子树的最左节点！
	 * 递归的思想就是，先找到比目标节点大的那个节点，然后对其左子树进行递归
	 * 要是其左子树为空，那么这个节点就是后继节点了。若是其左子树不为空，那么
	 * 找到当前节点的后继节点只需要进行递归 */

	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		if (root == null)
			return null;

		if (root.val <= p.val) {		// 对右边进行递归
			return inorderSuccessor2(root.right, p);
		} else {					// 对左边进行递归，
			TreeNode left = inorderSuccessor2(root.left, p);
			return (left != null) ? left : root;
		}
	}

	/** -------------------------------- Iterative version ---------------------------------- 
	 * 
	 * The inorder traversal of a BST is the nodes in ascending order. 
	 * To find a successor, you just need to find the smallest one that is larger than the 
	 * given value since there are no duplicate values in a BST. It just like the binary search
	 * in a sorted list. The time complexity should be O(h) where h is the depth of the
	 * result node. succ is a pointer that keeps the possible successor. Whenever you go left 
	 * the current root is the new possible successor, otherwise the it remains the same.
	 * 
	 * Only in a balanced BST O(h) = O(log n). In the worst case h can be as large as n. */

	public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
		TreeNode succ = null;
		
		while (root != null) {
			if (p.val < root.val) {		// 小于当前节点，succ 追踪当前节点，向左子树遍历
				succ = root;
				root = root.left;
			} else
				root = root.right;
		}
		
		return succ;
	}
}
