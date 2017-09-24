package binarytree;

import java.util.Stack;

/**
 * 285. Inorder Successor in BST
 * 
 * Given a binary search tree and a node in it, find the in-order successor of
 * that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null.
 */

public class InorderSuccessorBST {
	
	// -------------------------------- Iterative ----------------------------------
	
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
        	if (cur == p) {
        		TreeNode t = null;
        		if (cur.right != null) {
        			t = cur.right;
        			while (t.left != null)
        				t = t.left;
        			successor = t;
        		}
        		else {
        			successor = (stack.size() != 0) ? stack.peek() : null;
        			break;
        		}
        	}
        		
        	cur = cur.right;
        }
        
        return successor;
    }
    
	// -------------------------------- Recursion ----------------------------------

	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		if (root == null)
			return null;

		if (root.val <= p.val) {
			return inorderSuccessor2(root.right, p);
		} else {
			TreeNode left = inorderSuccessor2(root.left, p);
			return (left != null) ? left : root;
		}
	}

	/**
	 * -------------------------------- Recursion ---------------------------------- 
	 * 
	 * The inorder traversal of a BST is the
	 * nodes in ascending order. To find a successor, you just need to find the
	 * smallest one that is larger than the given value since there are no
	 * duplicate values in a BST. It just like the binary search in a sorted
	 * list. The time complexity should be O(h) where h is the depth of the
	 * result node. succ is a pointer that keeps the possible successor.
	 * Whenever you go left the current root is the new possible successor,
	 * otherwise the it remains the same.
	 * 
	 * Only in a balanced BST O(h) = O(log n). In the worst case h can be as
	 * large as n.
	 */

	public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
		TreeNode succ = null;
		while (root != null) {
			if (p.val < root.val) {
				succ = root;
				root = root.left;
			} else
				root = root.right;
		}
		return succ;
	}
}
