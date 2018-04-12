package binaryTree;

import java.util.Stack;

/** 572. Subtree of Another Tree
 * 
 * Given two non-empty binary trees s and t, check whether tree t has exactly
 * the same structure and node values with a subtree of s. A subtree of s is a
 * tree consists of a node in s and all of this node's descendants. The tree s
 * could also be considered as a subtree of itself. */

public class SubtreeOfAnotherTree_572 {
	
	/** For each node during pre-order traversal of s, use a recursive function
	 * isSame to validate if sub-tree started with this node is the same with t. */
	
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) 		return false;
		if (isSame(s, t)) 	return true;	// s == t
		
		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private boolean isSame(TreeNode s, TreeNode t) {
		if (s == null && t == null)		// both null
			return true;
		
		if (s == null || t == null)		// either s or t is null
			return false;

		if (s.val != t.val)				// values are not equal
			return false;

		return isSame(s.left, t.left) && isSame(s.right, t.right);	// check children
	}
	
	/** Firstly, serialize the two trees to strings.
	 * And then check if serialized tree s contains serialized tree t */
	
	public boolean isSubtree2(TreeNode s, TreeNode t) {
		
		// Java uses a naive contains algorithm so to ensure linear time, replace with KMP algorithm
	    return serialize(s).contains(serialize(t)); 
	}

	public String serialize(TreeNode root) {
		
	    StringBuilder sb = new StringBuilder();
	    serialize(root, sb);
	    
	    return sb.toString();
	}

	private void serialize(TreeNode cur, StringBuilder sb) {
		if (cur == null) {
			sb.append(",#");
			return;
		}
		
		sb.append("," + cur.val);
		
		serialize(cur.left, sb);
		serialize(cur.right, sb);
	}
	
	/**
	 * 
	 */
	
	public boolean isSubtree3(TreeNode s, TreeNode t) {
		
		String spreorder = generatepreorderString(s);
		String tpreorder = generatepreorderString(t);

		return spreorder.contains(tpreorder);
	}

	public String generatepreorderString(TreeNode s) {
		
		StringBuilder sb = new StringBuilder();
		Stack<TreeNode> stacktree = new Stack<>();
		stacktree.push(s);
		
		while (!stacktree.isEmpty()) {
			
			TreeNode poped = stacktree.pop();
			
			if (poped == null) 	sb.append(",#"); 
			else 				sb.append("," + poped.val);
			
			if (poped != null) {
				stacktree.push(poped.right);
				stacktree.push(poped.left);
			}
		}
		
		return sb.toString();
	}
}
