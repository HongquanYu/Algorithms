package binarytree;

public class CountCompleteTreeNode {
	
	// --------------------- Recursion -------------------------
	
	private int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left); // compute left subtree is enough
	}

	public int countNodes(TreeNode root) {
		int h = height(root);
		if (h < 0)				// empty tree
			return 0;
		else {
			if (height(root.right) == h - 1)				// height (right subtree) = height (left subtree) 
				return (1 << h) + countNodes(root.right);	// Number (left subtree is complete + root + right subtree): 2^h - 1 + 1 + root.right
			else	// right subtree height = h-2
				return (1 << h - 1) + countNodes(root.left);// Number (right subtree is complete + root + left subtree): 2^(h-1)-1 + 1 + root.left
		}
	}
	
	
	// --------------------- Iterative -------------------------
	
	int height2(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
	
    public int countNodes2(TreeNode root) {
        int nodes = 0, h = height(root);
        
        while (root != null) {
            if (height(root.right) == h - 1) {
                nodes += 1 << h;
                root = root.right;
            } else {
                nodes += 1 << h-1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }
}
