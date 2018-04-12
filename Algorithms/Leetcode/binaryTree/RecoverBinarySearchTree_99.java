package binaryTree;

/**
 * Add to List 99. Recover Binary Search Tree
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you
 * devise a constant space solution?
 */

public class RecoverBinarySearchTree_99 {
	
	/** In-order traversal solution */
	
	private TreeNode first = null;
	private TreeNode second = null;
	private TreeNode prev = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		inorderTraverse(root);	// 遍历找到两个错乱节点
		
		swap(first, second);		// 交换他们的位置
	}

	private void inorderTraverse(TreeNode root) {
		if (root == null) return;

		inorderTraverse(root.left);
		
		if (prev.val >= root.val) {		// 前一个节点比本节点大
			if (first == null)	first  = prev;		// 第一个还没找到，那么prev就是第一个
			if (	first != null)	second = root;		// 要是找到了第一个，那么第二个就得是
		}
		prev = root;

		inorderTraverse(root.right);
	}
	
	private void swap(TreeNode t1, TreeNode t2) {
		int t = t1.val;
		t1.val = t2.val;
		t2.val = t;
	}
	
	/* Morris Traversal */
	
	public void recoverTree2(TreeNode root) {
		TreeNode pre = null;
		TreeNode first = null, second = null;

		TreeNode temp = null;
		while (root != null) {
			if (root.left != null) {
				// connect threading for root
				temp = root.left;
				while (temp.right != null && temp.right != root)
					temp = temp.right;
				// the threading already exists
				if (temp.right != null) {
					if (pre != null && pre.val > root.val) {
						if (first == null) {
							first = pre;
							second = root;
						} else {
							second = root;
						}
					}
					pre = root;

					temp.right = null;
					root = root.right;
				} else {
					// construct the threading
					temp.right = root;
					root = root.left;
				}
			} else {
				if (pre != null && pre.val > root.val) {
					if (first == null) {
						first = pre;
						second = root;
					} else {
						second = root;
					}
				}
				pre = root;
				root = root.right;
			}
		}
		
		swap(first, second);
	}
}
