package binarySearchTree;

/** 333. Largest BST Subtree
 * 
 * Given a binary tree, find the largest subtree which is a Binary Search Tree
 * (BST), where largest means subtree with largest number of nodes in it. 
 * 
 * The return value is the subtree's size, which is 3.
 * 
 * Can you figure out ways to solve it with O(n) time complexity? */

public class LargestBSTSubtree_333 {
	
	/** Bottom Up Recursion! */
	
	private class Result {  
        int size;			// size of current tree
        int lower, upper;	// range of current tree [rangeLower, rangeUpper]
        
        Result(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }
    
    private int max = 0;
    
	public int largestBSTSubtree(TreeNode root) {
		if (root == null) 	return 0;
		
		traverse(root, null);
		
		return max;
	}

	private Result traverse(TreeNode root, TreeNode parent) {
		if (root == null)
			return new Result(0, parent.val, parent.val);
		
		Result left = traverse(root.left, root);
		Result right = traverse(root.right, root);
		
		// 检查当前节点和 左右子节点 是否不满足 BST 的定义！
		if (left.size == -1 || right.size == -1 || root.val < left.upper || root.val > right.lower)
			return new Result(-1, 0, 0);
		
		// 当前的 BST 的size，
		int size = left.size + 1 + right.size;
		max = Math.max(size, max);
		
		return new Result(size, left.lower, right.upper);
	}
	
	// ------------------------------------------------------------------------------------------------
	
	public int largestBSTSubtree2(TreeNode root) {
		if (root == null) 	return 0;
		
		if (root.left == null && root.right == null)
			return 1;
		
		if (isValid(root, null, null))
			return countNode(root);
		
		return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
	}

	public boolean isValid(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		if (min != null && min >= root.val)
			return false;
		if (max != null && max <= root.val)
			return false;
		return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
	}

	public int countNode(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		return 1 + countNode(root.left) + countNode(root.right);
	}
	
	
	/** Since this is not an overall boolean check, and each subtree can decide
	 * if itself is a BST, and update a global size variable, I have chosen to
	 * decide BST at each subtree, and pass a 3-element array up. If subtree is
	 * not BST, size will be -1, and parent tree will not be BST
	 * 
	 * time complexity is O(n), since each node is visited exactly once */
	
	private int largestBSTSubtreeSize = 0;

	public int largestBSTSubtree3(TreeNode root) {
		helper(root);
		return largestBSTSubtreeSize;
	}

	private int[] helper(TreeNode root) {
		// return 3-element array:
		// # of nodes in the subtree, leftmost value, rightmost value
		// # of nodes in the subtree will be -1 if it is not a BST
		int[] result = new int[3];
		if (root == null)
			return result;
		
		int[] leftResult = helper(root.left);
		int[] rightResult = helper(root.right);
		if ((leftResult[0] == 0 || leftResult[0] > 0 && leftResult[2] <= root.val)
				&& (rightResult[0] == 0 || rightResult[0] > 0 && rightResult[1] >= root.val)) {
			int size = 1 + leftResult[0] + rightResult[0];
			largestBSTSubtreeSize = Math.max(largestBSTSubtreeSize, size);
			int leftBoundary = leftResult[0] == 0 ? root.val : leftResult[1];
			int rightBoundary = rightResult[0] == 0 ? root.val : rightResult[2];
			result[0] = size;
			result[1] = leftBoundary;
			result[2] = rightBoundary;
		} else	result[0] = -1;
		
		return result;
	}
}
