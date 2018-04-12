package binaryTree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/** 606. Construct String from Binary Tree
 * 
 * You need to construct a string consists of parenthesis and integers from a
 * binary tree with the pre-order traversing way.
 * 
 * The null node needs to be represented by empty parenthesis pair "()". And you
 * need to omit all the empty parenthesis pairs that don't affect the one-to-one
 * mapping relationship between the string and the original binary tree. */

public class TreeToString_606 {
	
	/** Recursion */
	
	public String tree2str(TreeNode t) {
		if (t == null)
			return "";
		if (t.left == null && t.right == null)
			return t.val + "";
		if (t.right == null)
			return t.val + "(" + tree2str(t.left) + ")";
		return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
	}
	
	/** Iterative Method Using stack */
	
	public String tree2str2(TreeNode t) {
		if (t == null)
			return "";
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(t);
		Set<TreeNode> visited = new HashSet<>();
		StringBuilder s = new StringBuilder();
		
		while (!stack.isEmpty()) {
			t = stack.peek();
			
			if (visited.contains(t)) {
				stack.pop();
				s.append(")");
			} else {
				visited.add(t);
				s.append("(" + t.val);
				
				if (t.left == null && t.right != null)
					s.append("()");
				if (t.right != null)
					stack.push(t.right);
				if (t.left != null)
					stack.push(t.left);
			}
		}
		
		return s.substring(1, s.length() - 1);
	}
}
