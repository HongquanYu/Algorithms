package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostOrder_145 {
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (root == null) return ans;
		
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			ans.addFirst(cur.val);
			if (cur.left != null) {
				stack.push(cur.left);
			}
			if (cur.right != null) {
				stack.push(cur.right);
			} 
		}
		return ans;
	}
	
	public List<Integer> postorderTraversal2(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<Integer>();
		TreeNode lnv = null;
		Stack<TreeNode> s = new Stack<TreeNode>();
		
		while(!s.isEmpty() || root != null) {
			if (root != null) {
				s.push(root);
				root = root.left;
			} else {
				TreeNode pn = s.peek();
				if (pn.right != null && lnv != pn)
					root = pn.right;
				else {
					ans.add(pn.val);
					lnv = s.pop();
				}
			}
		}
		
		return ans;
	}
}
