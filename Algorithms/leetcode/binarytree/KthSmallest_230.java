package binarytree;

import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST
 * 
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 * 
 * Note: You may assume k is always valid
 * 
 * Follow up: What if the BST is modified (insert/delete operations) often and
 * you need to find the kth smallest frequently? How would you optimize the
 * kthSmallest routine? */

public class KthSmallest_230 {
	
	// ----------------------------  DFS - Iterative   ----------------------------------
	
    public int kthSmallest(TreeNode root, int k) {
    	int count = 0;
    	int res = -1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        
        while(cur != null || !stack.isEmpty()) {
        	while(cur != null) {
        		stack.push(cur);
        		cur = cur.left;
        	}
        	
        	cur = stack.pop();
        	if (++count == k) {
        		res = cur.val;
        		break;
        	}
        	
        	cur = cur.right;
        }
		return res;
    }
    
    // ----------------------------  Recursion   ----------------------------------
    
    private static int number = 0;
    private static int count = 0;

	public int kthSmallestR(TreeNode root, int k) {
		count = k;
		helper(root);
		return number;
	}

	private void helper(TreeNode n) {
		if (n.left != null)
			helper(n.left);
		count--;
		if (count == 0) {
			number = n.val;
			return;
		}
		if (n.right != null)
			helper(n.right);
	}
    
    // ----------------------------  Binary Search ----------------------------------
    
	public int kthSmallestB(TreeNode root, int k) {
		int count = countNodes(root.left);
		if (k <= count) {
			return kthSmallest(root.left, k);
		} else if (k > count + 1) {
			return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
		}

		return root.val;
	}

	private int countNodes(TreeNode n) {
		if (n == null)
			return 0;

		return 1 + countNodes(n.left) + countNodes(n.right);
	}
}
