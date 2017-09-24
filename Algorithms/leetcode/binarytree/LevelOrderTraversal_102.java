package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal_102 {
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();		// level order data structure
		
		if (root == null)
			return result;
		
		queue.offer(root);

		while (!queue.isEmpty()) {
			int numLevelNode = queue.size();					// nodes in queue are all in current level
			List<Integer> list = new LinkedList<Integer>();		// to store all level nodes

			for (int i = 0; i < numLevelNode; i++) {
				TreeNode tmp = queue.poll();
				if (tmp.left != null)
					queue.offer(tmp.left);
				if (tmp.right != null)
					queue.offer(tmp.right);
				list.add(tmp.val);
			}
			result.add(list);									// to store all nodes in current level
		}

		return result;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		TreeNode t = new TreeNode(20);
		t.left = new TreeNode(15);
		t.right = new TreeNode(7);
		root.right = t;
		
		for( List tmp : levelOrderRecursion(root))
			System.out.println(tmp);
	}
	
	
	public static List<List<Integer>> levelOrderRecursion(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(res, root, 0);
		return res;
	}
	
	private static void helper(List<List<Integer>> res, TreeNode root, int height) {
		if (root == null)	// current node is null, return
			return;
		
		if (height >= res.size())	// build list for next level
			res.add(new LinkedList<Integer>());
		
		res.get(height).add(root.val);	// add to list
		
		helper(res, root.left, height + 1);
		helper(res, root.right, height + 1);
	}
}