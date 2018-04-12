package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 257. Binary Tree Paths 
 * 
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * For example, given the following binary tree:
 * 	 1
 *  / \    
 * 2   3
 *  \
 *   5
 * All root-to-leaf paths are: ["1->2->5", "1->3"]
 */

public class BinaryTreePaths_257 {
	
	/** ----------------------------  Recursion   ----------------------------------
	 *  The idea is to concatenate root and subtrees recursively */
	
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new LinkedList<>();

		if (root == null) 	// base && boundary
			return res;

		String r = Integer.toString(root.val);
		
		// 先检查当前左右子树的结果，都为空则加上当前
		if (binaryTreePaths(root.left).isEmpty() &&
				binaryTreePaths(root.right).isEmpty()) {
			res.add(r);
			return res;
		}
		
		// concatenate left subtrees 
		for (String s : binaryTreePaths(root.left))
			res.add(r + "->" + s);

		// concatenate right subtrees
		for (String s : binaryTreePaths(root.right))
			res.add(r + "->" + s);
		
		return res;
	}
    
    // -------------------------------------------------------------------------
    
	public List<String> binaryTreePaths2(TreeNode root) {
		List<String> answer = new ArrayList<String>();
		
		if (root != null) 	searchBT(root, "", answer);
		
		return answer;
	}

	private void searchBT(TreeNode root, String path, List<String> answer) {
		if (root.left == null && root.right == null)		// 当遇到一个叶子节点
			answer.add(path + root.val);
		
		if (root.left != null)		// 左子树不为空，递归，这里就把当前的结果给复制了一份了
			searchBT(root.left, path + root.val + "->", answer);
		
		if (root.right != null)		// 右子树不为空，递归，这里就把当前的结果给复制了一份了
			searchBT(root.right, path + root.val + "->", answer);
	}
    
    /** ----------------------------  Iterative   ----------------------------------
     *  First solution: Using a queue to BFS
     *  Second solution: Using a stack to DFS
     */
	
	public List<String> binaryTreePathsBFS(TreeNode root) {
		
		List<String> result = new ArrayList<String>();
		if (root == null) 	return result;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<String> qStr = new LinkedList<String>();

		queue.add(root);
		qStr.add("");
		
		while (!queue.isEmpty()) {
			TreeNode cur = queue.remove();
			String curStr = qStr.remove();

			if (cur.left == null && cur.right == null)	// leaf
				result.add(curStr + cur.val);
			if (cur.left != null) {			// left subtree exist
				queue.add(cur.left);
				qStr.add(curStr + cur.val + "->");
			}
			if (cur.right != null) {		// right subtree exist
				queue.add(cur.right);
				qStr.add(curStr + cur.val + "->");
			}
		}
		return result;
	}
	
	public List<String> binaryTreePathsDFS(TreeNode root) {
		List<String> result = new ArrayList<String>();
		if (root == null) 	return result;
		
		Stack<TreeNode> sNode = new Stack<TreeNode>();
		Stack<String> sStr = new Stack<String>();

		sNode.push(root);
		sStr.push("");
		
		while (!sNode.isEmpty()) {
			TreeNode cur = sNode.pop();
			String curStr = sStr.pop();

			if (cur.left == null && cur.right == null)	// 
				result.add(curStr + cur.val);
			
			if (cur.left != null) {		// 
				sNode.push(cur.left);
				sStr.push(curStr + cur.val + "->");
			}
			if (cur.right != null) {	// 
				sNode.push(cur.right);
				sStr.push(curStr + cur.val + "->");
			}
		}
		return result;
	}
}
