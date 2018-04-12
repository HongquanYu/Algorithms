package binarySearchTree;

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
	
	TreeNode firstElement = null;
	TreeNode secondElement = null;
	TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		inorderTraverse(root);
		
		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}

	private void inorderTraverse(TreeNode root) {
		if (root == null)
			return;

		inorderTraverse(root.left);

		if (firstElement == null && prevElement.val >= root.val)
			firstElement = prevElement;
		
		if (firstElement != null && prevElement.val >= root.val)
			secondElement = root;
		
		prevElement = root;

		inorderTraverse(root.right);
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
		// swap two node values;
		if (first != null && second != null) {
			int t = first.val;
			first.val = second.val;
			second.val = t;
		}
	}
}
