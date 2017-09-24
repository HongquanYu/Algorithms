package binarytree;

public class CountUniValueSubtrees_250 {
	
	// --------------------- Recursion -------------------------
	
	int count = 0;

	private boolean all(TreeNode root, int val) {
		if (root == null)
			return true;
		if (!all(root.left, root.val) | !all(root.right, root.val))
			return false;
		
		count++;
		return root.val == val;
	}

	public int countUnivalSubtrees(TreeNode root) {
		all(root, 0);
		return count;
	}
	
	
	// --------------------- Iterative -------------------------
	
	public int countUnivalSubtrees2(TreeNode root) {
		int[] count = new int[1];
		helper(root, count);
		return count[0];
	}

	private boolean helper(TreeNode node, int[] count) {
		if (node == null) {
			return true;
		}
		boolean left = helper(node.left, count);
		boolean right = helper(node.right, count);
		if (left && right) {
			if (node.left != null && node.val != node.left.val) {
				return false;
			}
			if (node.right != null && node.val != node.right.val) {
				return false;
			}
			count[0]++;
			return true;
		}
		return false;
	}
}
