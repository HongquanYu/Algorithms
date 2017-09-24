package binarytree;

/**
 * 450. Delete Node in a BST
 * 
 * Given a root node reference of a BST and a key, delete the node with the
 * given key in the BST. Return the root node reference (possibly updated) of
 * the BST.
 * 
 * Basically, the deletion can be divided into two stages:
 * 
 * Search for a node to remove. If the node is found, delete the node. Note:
 * Time complexity should be O(height of tree).
 */

public class DeleteNodeBST_450 {
	
	/**
	 * Steps:
	 * 
	 * 1, Recursively find the node that has the same value as the key, while setting the left/right
	 * 	nodes equal to the returned subtree 
	 * 2, Once the node is found, have to handle the below 4 cases:
	 *  - node doesn't have left or right - return null
	 *  - node only has left subtree- return the left subtree
	 *  - node only has right subtree- return the right subtree
	 *  - node has both left and right - find the minimum value in the right subtree, set that value
	 * 		to the currently found node, then recursively delete the minimum value in
	 * 		the right subtree
	 * */
	
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null)
			return null;
		
		if (key < root.val)
			root.left = deleteNode(root.left, key);
		else if (key > root.val)
			root.right = deleteNode(root.right, key);
		else {
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			TreeNode minNode = findMin(root.right);
			root.val = minNode.val;
			root.right = deleteNode(root.right, root.val);
		}
		return root;
	}

	private TreeNode findMin(TreeNode node) {
		while (node.left != null)
			node = node.left;
		
		return node;
	}
	
	/** Iterative solution */
	
	public TreeNode deleteNode2(TreeNode root, int key) {
		TreeNode cur = root;
		TreeNode pre = null;
		
		while (cur != null && cur.val != key) {
			pre = cur;
			if (key < cur.val)
				cur = cur.left;
			else if (key > cur.val)
				cur = cur.right;
			
		}
		
		if (pre == null)
			return deleteRootNode(cur);
		
		if (pre.left == cur)
			pre.left = deleteRootNode(cur);
		else
			pre.right = deleteRootNode(cur);
		
		return root;
	}
	
	private TreeNode deleteRootNode(TreeNode root) {
		if (root == null)
			return null;
		
		if (root.left == null)
			return root.right;
		
		if (root.right == null)
			return root.left;
		
		TreeNode next = root.right;
		TreeNode pre = null;
		
		for (; next.left != null; pre = next, next = next.left) ;
		
		next.left = root.left;
		
		if (root.right != next) {
			pre.left = next.right;
			next.right = root.right;
		}
		
		return next;
	}
}
