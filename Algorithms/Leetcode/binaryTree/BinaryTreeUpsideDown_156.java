package binaryTree;

public class BinaryTreeUpsideDown_156 {
	
	/** recursion */
	
	public TreeNode upsideDownBinaryTree(TreeNode root) {	
		if (root == null || root.left == null)	// reverse the binary tree
			return root;
		
		// 对当前节点（看作一个node含有左右孩子）进行旋转，然后再对左节点进行迭代
		root.left.left = root.right; 	// node 2 left children
		root.left.right = root; 			// node 2 right children
		root.left = null;
		root.right = null;
		return upsideDownBinaryTree(root.left);		// recursive reach left bottom
	}
	
	/** Iterative */
	
	public TreeNode upsideDownBinaryTree2(TreeNode root) {	// iterative
	    TreeNode curr = root;		// 当前处理节点
	    TreeNode right = null;		// 当前右孩子，即下一个要处理的节点
	    TreeNode parent = null;		// parent暂存当前节点，作为下一个处理节点的右孩子
	    
	    while(curr != null) {		// 循环直到当前处理节点为空！
	    		TreeNode left = curr.left;
	        
	        // 对当前节点进行旋转，并保存右孩子
	        curr.left = right;
	        right = curr.right;
	        curr.right = parent;
	        
	        parent = curr;		// 更新当前处理节点到下一个节点
	        curr = left;
	    }
	    
	    return parent;	// curr == null 跳出循环，所以要返回最后一个non-null节点，也就是parent
	}  
}
