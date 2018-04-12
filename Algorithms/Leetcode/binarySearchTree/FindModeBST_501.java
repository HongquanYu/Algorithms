package binarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. Find Mode in Binary Search Tree
 * 
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
 * most frequently occurred element) in the given BST. */

public class FindModeBST_501 {
	
	/** Two passes. One to find the highest number of occurrences of any value,
	 * and then a second pass to collect all values occurring that often. Both
	 * passes keep track of the current value etc, and the second pass
	 * additionally collects the modes in the result array. I took the value
	 * handling out of the in-order traversal into its own function for clarity.
	 * Also, this way you could very easily replace my recursive in-order
	 * traversal with for example Morris traversal. Then you wouldn't even need
	 * to disregard the recursion stack space in order to claim O(1) extra space */
	
	private int currVal;				// 追踪当前的 mode 的最大值
	private int currCount = 0;		// 
	private int maxCount = 0;		// 追踪当前 mode 的最大的频度
	private int modePtr = 0;			// 作为加入 mode 结果的指针
	private int[] modes;
	
	public int[] findMode(TreeNode root) {
		inorder(root);
		
		modes = new int[modePtr];	// 总共有 modePtr 个 modes
		modePtr = 0;
		currCount = 0;
		
		inorder(root);
		
		return modes;
	}
	
	/** 中序遍历并调用 handleValue */
	
	private void inorder(TreeNode root) {
		if (root == null)
			return;
		
		inorder(root.left);
		handleValue(root.val);
		inorder(root.right);
	}
	
	/** 检查当前节点的频度并且和最大频度相比，并更新相关值 */
	
	private void handleValue(int val) {
		if (val != currVal) {
			currVal = val;
			currCount = 0;
		}
		currCount++;
		if (currCount > maxCount) {
			maxCount = currCount;
			modePtr = 1;
		} else if (currCount == maxCount) {
			if (modes != null)
				modes[modePtr++] = currVal;
		}
	}

	
	/** Here's Morris traversal, just replace my previous inorder function with
	 * this. I hadn't realized it earlier, but having my separate handleValue
	 * function doesn't just nicely separate the traversal logic from this
	 * problem's logic, but it's also beneficial for Morris traversal because
	 * I'm calling handleValue from two different places in the code! */
	
	private void inorder2(TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			if (node.left == null) {
				handleValue(node.val);
				node = node.right;
			} else {
				TreeNode prev = node.left;
				while (prev.right != null && prev.right != node)
					prev = prev.right;
				if (prev.right == null) {
					prev.right = node;
					node = node.left;
				} else {
					prev.right = null;
					handleValue(node.val);
					node = node.right;
				}
			}
		}
	}
	
	// -------------------------------------------------------------------------------
	
	Integer prev = null;		// 指向前一个遍历的元素
	int freq = 1;			// 当前元素的频度
	int modeFreq = 0;		// mode 的频度

	public int[] findMode2(TreeNode root) {
		if (root == null)
			return new int[0];

		List<Integer> list = new ArrayList<>();
		inorderTraverse(root, list);

		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	private void inorderTraverse(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		
		// 递归访问左子树
		inorderTraverse(root.left, list);		
		
		// 处理当前 root 节点
		if (prev != null) {
			if (root.val == prev) 	freq++;
			else 					freq = 1;
		}
		if (freq > modeFreq) {			// 如果当前元素比 mode 小，则清空list并重置计数器
			modeFreq = freq;
			list.clear();
			list.add(root.val);
		} else if (freq == modeFreq) {	// 当前元素和 mode 一样的频度，加入list
			list.add(root.val);
		}
		prev = root.val;
		
		// 递归访问左子树
		inorderTraverse(root.right, list);	
	}
}
