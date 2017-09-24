package binarytree;

public class UpsideDownBinaryTree_156 {
	public TreeNode upsideDownBinaryTree(TreeNode root) {	// recursion
		if (root == null || root.left == null)	// reverse the binary tree
			return root;

		TreeNode newRoot = upsideDownBinaryTree(root.left);		// recursive reach left bottom
		root.left.left = root.right; 	// node 2 left children
		root.left.right = root; 		// node 2 right children
		root.left = null;
		root.right = null;
		return newRoot;
	}
	
	public TreeNode upsideDownBinaryTree2(TreeNode root) {	// iterative
	    TreeNode curr = root, next = null, temp = null, prev = null;
	    
	    while(curr != null) {
	        next = curr.left;
	        
	        // swapping nodes now, need temp to keep the previous right child
	        curr.left = temp;
	        temp = curr.right;
	        curr.right = prev;
	        
	        prev = curr;
	        curr = next;
	    }
	    return prev;
	}  
}
