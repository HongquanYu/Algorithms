package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PreOrder_144 {
    public List<Integer> preorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(res, root);
        
        return res;
    }
    
    private static void helper(List<Integer> res, TreeNode root) {
    	if (root == null)
    		return;
    	
    	res.add(root.val);
    	helper(res, root.left);
    	helper(res, root.right);
    }
    
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (root == null)
        	return res;
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        
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
}





















