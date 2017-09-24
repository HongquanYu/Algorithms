package binarytree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TraversalBuildTree_105_106 {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd)	// boundary check
			return null;

		TreeNode root = new TreeNode(preorder[preStart]);		// pre-order[0] is root
		int inIndex = 0;										// Index of current root in in-order

		for (int i = inStart; i <= inEnd; i++) 					// find root in in-order
			if (inorder[i] == root.val)
				inIndex = i;

		root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);		// exclude root from pre-order
		// preStart + inIndex - inStart + 1: index of root of right child
		root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);

		return root;
	}
	
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < inorder.length; i++)	// Map every in-order element to HashMap
			inMap.put(inorder[i], i);

		TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
		return root;
	}

	public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> inMap) {
		if (preStart > preEnd || inStart > inEnd)	// boundary check
			return null;

		TreeNode root = new TreeNode(preorder[preStart]);
		int rootIdx = inMap.get(root.val);			// get root index in the in-order
		int numsLeft = rootIdx - inStart;			// nodes number of left subtree

		root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, rootIdx - 1, inMap);
		root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, rootIdx + 1, inEnd, inMap);

		return root;
	}
	
	// -------------build from PRE and IN order - Iterative solution ---------
	public static TreeNode buildTree3(int[] preorder, int[] inorder) {
		if (inorder.length == 0)
			return null; // boundary check

		Stack<TreeNode> stack = new Stack<TreeNode>();		// to store nodes from pre-order sequence
		TreeNode root = new TreeNode(Integer.MIN_VALUE); 	// fake root
		stack.push(root);

		int i = 0, j = 0;			// i - index of pre-order; j - index of in-order
		TreeNode popedItem = null;	// 
		TreeNode cur = root;		// 

		while (j < inorder.length) {
			if (stack.peek().val == inorder[j]) { 	// if there's a match of in-order and stack
				popedItem = stack.pop();
				j++;	// increase in-order index
			} else if (popedItem != null) {			// current node in 
				cur = new TreeNode(preorder[i]);	// 
				popedItem.right = cur;
				popedItem = null;
				stack.push(cur);
				i++;
			} else {								// 
				cur = new TreeNode(preorder[i]);
				stack.peek().left = cur;
				stack.push(cur);
				i++;
			}
		}
		return root.left;
	}

	// -------------build from POST and IN order---------
	public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null || inorder.length != postorder.length)
			return null;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; ++i) // in-order into hashmap
			map.put(inorder[i], i);
		return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
	}

	private TreeNode helper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
			HashMap<Integer, Integer> hm) {
		if (ps > pe || is > ie) 	// boundary check
			return null;

		TreeNode root = new TreeNode(postorder[pe]); 	// get root
		int ri = hm.get(postorder[pe]); 				// get index of root node in in-order

		root.left = helper(inorder, is, ri - 1, postorder, ps, ps + ri - is - 1, hm);
		root.right = helper(inorder, ri + 1, ie, postorder, ps + ri - is, pe - 1, hm);

		return root;

	}
	// -------------------------------------------------
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		TreeNode left = new TreeNode(8);
		left.left = new TreeNode(1);
		root.left = left;
		
		TreeNode sub = new TreeNode(3);
		sub.right = new TreeNode(6);
		TreeNode right = new TreeNode(4);
		right.left = sub;
		right.right = new TreeNode(2);
		root.right = right;
		
		int[] pre = {5, 8, 1, 4, 3, 6, 2};
		int[] in = {1, 8, 5, 3, 6, 4, 2};
		
		buildTree3(pre, in);
	}
}
