package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** 112. Path Sum
 * 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum. */

public class PathSum_112 {
	
	/** ----------------------------  Recursion   ----------------------------------
	 * The basic idea is to subtract the value of current node from sum until it
	 * reaches a leaf node and the subtraction equals 0, then we know that we
	 * got a hit. Otherwise the subtraction at the end could not be 0. */
	
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)	// base && boundary
			return false;
		
		// Find one path from root to leaf
		if (root.left == null && root.right == null && sum - root.val == 0)	
			return true;

		return hasPathSum(root.left, sum - root.val)		// check left subtree
				|| hasPathSum(root.right, sum - root.val);	// check right subtree
	}
	
	/** ---------------------------- Iterative ----------------------------------
	 * In the post-order traversal, the node will be removed from the stack only
	 * when the right sub-tree has been visited.so the path will be stored in
	 * the stack. we can keep check the SUM, the length from root to leaf node.
	 * at leaf node, if SUM == sum, OK, return true. After post-order traversal,
	 * return false.
	 */
	
	public boolean hasPathSumIterative(TreeNode root, int sum) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null, cur = root;
		int sum2 = 0;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {		// reach left leaf
				stack.push(cur);
				sum2 += cur.val;
				cur = cur.left;
			}
			cur = stack.pop();
			if (cur.right == null && cur.right == null && sum2 == sum)	// find a path
				return true;
			if (cur.right != null && pre != cur.right) {	// avoid go back
				cur = cur.right;	// go to right branch
			} else { 		// bottom-up
				pre = cur;
				stack.pop();
				sum2 -= cur.val;
				cur = null;
			}
		}
		return false;
	}
	
	/** 113. Path Sum II
	 *  Given a binary tree and a sum, find all root-to-leaf
	 *  paths where each path's sum equals the given sum. */
	
	// --------------------------------- DFS - Backtrace ------------------------------------------
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<Integer> currentResult = new LinkedList<Integer>();

		pathSum(root, sum, currentResult, result);

		return result;
	}

	public void pathSum(TreeNode root, int sum, List<Integer> currentResult, List<List<Integer>> result) {

		if (root == null)	return;
		
		currentResult.add(root.val);	// add current node into list
		
		if (root.left == null && root.right == null && sum == root.val) {	// find one path
			result.add(new LinkedList(currentResult));
			currentResult.remove(currentResult.size() - 1);	// don't forget to remove the last integer
			return;
		} else {	
			pathSum(root.left, sum - root.val, currentResult, result);
			pathSum(root.right, sum - root.val, currentResult, result);
		}
		
		currentResult.remove(currentResult.size() - 1);
	}
	
	// ----------------------------------------------------------------------------------------------
	// Save intermediate result into stack and save the stack into result array once its sum == required sum.
	
	private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

	public void pathSumInner(TreeNode root, int sum, Stack<Integer> path) {
		path.push(root.val);
		if (root.left == null && root.right == null)			// reach a leaf
			if (sum == root.val)
				resultList.add(new ArrayList<Integer>(path));
		if (root.left != null)
			pathSumInner(root.left, sum - root.val, path);
		if (root.right != null)
			pathSumInner(root.right, sum - root.val, path);
		path.pop();
	}

	public List<List<Integer>> pathSum2(TreeNode root, int sum) {
		if (root == null)
			return resultList;
		Stack<Integer> path = new Stack<Integer>();
		pathSumInner(root, sum, path);
		return resultList;
	}
}
