package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal_144 {

	/** Iterative Solution: time: N, space: N */
    
	public List<Integer> preorderTraversalIterative(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();

		if (root == null)
			return res;

		Stack<TreeNode> s = new Stack<TreeNode>() {{ push(root); }};

		while (!s.isEmpty()) {
			TreeNode t = s.pop();
			res.add(t.val);

			if (t.right != null)
				s.push(t.right);
			if (t.left != null)
				s.push(t.left);
		}

		return res;
	}
	
	/** 不用stack存所有的元素，只存当前元素的右孩子，向左子树遍历 time: N, space: N */
    
    public List<Integer> preorderTraversalIterative2(TreeNode root) {
	    	List<Integer> res = new ArrayList<Integer>();
	    	Stack<TreeNode> rights = new Stack<TreeNode>();
	    	
	    	while (root != null) {
	    		res.add(root.val);
	    		if (root.right != null)
	    			rights.push(root.right);
	    		root = root.left;
	    		
	    		if (root == null && !rights.isEmpty())
	    			root = rights.pop();
	    	}
    	
        return res;
    }
	
    /** Morris Traversal: time N, Space 1 */
	
	public List<Integer> morrisPreorder(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		
		while (root != null) {
			if (root.left == null) {			// 左子树为空，访问当前节点并转向右子树
				res.add(root.val);
				root = root.right;
			} else {						// 找到左孩子的最底层右边的后代
				TreeNode tmp = root.left;
				while (tmp.right != null && tmp.right != root)
					tmp = tmp.right;
				
				if (tmp.right == root) {		// 已经访问过了
					tmp.right = null;
					root = root.right;
				} else {						// 访问当前节点并把其右指针指向当前节点
					res.add(root.val);
					tmp.right = root;
					root = root.left;
				}
			}
		}
		
		return res;
	}
	
	/** Recursion Solution: time: N, space: N */
	
    public List<Integer> preorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        recursion(res, root);
        return res;
    }
    
    private static void recursion(List<Integer> res, TreeNode root) {
	    	if (root == null)
	    		return;
	    	
	    	res.add(root.val);
	    	recursion(res, root.left);
	    	recursion(res, root.right);
    }
    
}
