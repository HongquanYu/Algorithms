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
    
}
