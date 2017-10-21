package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversal_94 {
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode pointer = root;
        
        while (!s.isEmpty() || pointer != null) {
        	while(pointer != null) {
        		s.push(pointer);
        		pointer = pointer.left;
        	}
        	pointer = s.pop();
        	res.add(pointer.val);
        	pointer = pointer.right;
        }

        return res;
    }
    
    public List<Integer> inorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
        	return res;
        
        res.addAll(inorderTraversalRecursion(root.left));
        res.add(root.val);
        res.addAll(inorderTraversalRecursion(root.right));
        
        return res;
    }
    
    /* Morris Traversal */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		TreeNode curr = root;
		TreeNode pre;
		while (curr != null) {
			if (curr.left == null) {
				res.add(curr.val);
				curr = curr.right; // move to next right node
			} else { // has a left subtree
				pre = curr.left;
				while (pre.right != null) { // find rightmost
					pre = pre.right;
				}
				pre.right = curr; // put cur after the pre node
				TreeNode temp = curr; // store cur node
				curr = curr.left; // move cur to the top of the new tree
				temp.left = null; // original cur left be null, avoid infinite loops
			}
		}
		return res;
	}
    
}
