package binaryTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105_106 {
	
	/** 从前序和中序建树，我们首先在 inorder 里找到根节点（ preorder 的第一个节点），并建立此节点
	 *  然后再对其左子树和右子树进行迭代建立子树，分别用当前root的左右孩子指向迭代的方法
	 *  这里需要注意的是：
	 *  inRoot - inStart + 1 代表左子树的长度
	 *  将它加上 preStart 即是其在 preorder 里面的右孩子的首个节点（根节点） */
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return build(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode build(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd)	// boundary check
			return null;

		TreeNode root = new TreeNode(preorder[preStart]);		// pre-order[0] is root
		int inRoot = 0;										// Index of current root in in-order

		for (int i = inStart; i <= inEnd; i++) 				// find root in in-order
			if (inorder[i] == root.val)
				inRoot = i;

		root.left = build(preStart + 1, inStart, inRoot - 1, preorder, inorder);
		root.right = build(preStart + (inRoot - inStart) + 1, inRoot + 1, inEnd, preorder, inorder);

		return root;
	}
	
	/** 方法2: 使用 HashMap 存储 inorder 的元素值和索引， */
	
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < inorder.length; i++)	// Map every in-order element to HashMap
			inMap.put(inorder[i], i);

		return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
	}

	public TreeNode buildTree(int[] pre, int ps, int pEnd, int[] in, int is, int inEnd, Map<Integer, Integer> inMap) {
		if (ps > pEnd || is > inEnd) 	// base case | boundary check
			return null;

		TreeNode root = new TreeNode(pre[ps]);
		int rootIdx = inMap.get(root.val); 		// 找到当前根节点在中序中的索引
		int numsLeft = rootIdx - is; 			// nodes number of left subtree

		root.left = buildTree(pre, ps + 1, ps + numsLeft, in, is, rootIdx - 1, inMap);		// 进行迭代
		root.right = buildTree(pre, ps + numsLeft + 1, pEnd, in, rootIdx + 1, inEnd, inMap);	// 

		return root;
	}
	
	// -------------build from PRE and IN order - Iterative solution ---------
	public static TreeNode buildTree3(int[] preorder, int[] inorder) {
		if (inorder.length == 0)		 // boundary check
			return null;

		Stack<TreeNode> stack = new Stack<TreeNode>();		// 保存 前序 队列
		TreeNode root = new TreeNode(Integer.MIN_VALUE); 		// fake root
		stack.push(root);

		int pre = 0, In = 0;			// 两个索引分别指向两个序列
		TreeNode popedItem = null;	// 暂存栈弹出元素
		TreeNode cur = root;		// 指针

		while (In < inorder.length) {
			if (stack.peek().val == inorder[In]) {	// 找到了当前树的根节点，存于 popedItem 中
				popedItem = stack.pop();
				In++;
			} else if (popedItem != null) {			// 说明之前已经找到了 root，建立节点并添加到树中
				cur = new TreeNode(preorder[pre]);
				popedItem.right = cur;
				popedItem = null;
				stack.push(cur);
				pre++;
			} else {	
				cur = new TreeNode(preorder[pre]);
				stack.peek().left = cur;
				stack.push(cur);
				pre++;
			}
		}
		
		return root.left;
	}

	// -------------build from POST and IN order---------
	public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null || inorder.length != postorder.length)
			return null;

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; ++i) // in-order into hashmap
			map.put(inorder[i], i);
		
		return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
	}

	private TreeNode helper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, HashMap<Integer, Integer> map) {
		if (ps > pe || is > ie) 	// boundary check
			return null;

		TreeNode root = new TreeNode(postorder[pe]); 	// get root
		int inRoot = map.get(postorder[pe]); 				// get index of root node in in-order

		root.left = helper(inorder, is, inRoot - 1, postorder, ps, ps + inRoot - is - 1, map);
		root.right = helper(inorder, inRoot + 1, ie, postorder, ps + inRoot - is, pe - 1, map);

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
