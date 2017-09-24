package binarytree;

public class LowestCommonAncestor_235_236 {
	
	// --------------------- 235-------------------------
	public TreeNode lowestCommonAncestorRecursion(TreeNode root, TreeNode p, TreeNode q) {
		if (root.val > p.val && root.val > q.val) { // both in left subTree
			return lowestCommonAncestorRecursion(root.left, p, q);
		} else if (root.val < p.val && root.val < q.val) { // both in right subTree
			return lowestCommonAncestorRecursion(root.right, p, q);
		} else { // root is LCA
			return root;
		}
	}
	
	public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
	    while ((root.val - p.val) * (root.val - q.val) > 0) {
	    	if (p.val < root.val)
	    		root = root.left;
	    	else
	    		root = root.right;
	    }
	    return root;
	}
	
	// --------------------- 236-------------------------
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)
			return root;
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left == null)
			return right;
		else if (right == null)
			return left;
		else
			return root;
	}
}
