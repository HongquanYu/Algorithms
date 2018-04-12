package binaryTree;

public class CountUniValueSubtrees_250 {
	
	/** 思想是：
	 *  递归地从根节点向下找，对于每一个子树是 UniValue Tree 都给 + 1，本递归是从下至上递归！
	 *  递归方法检查当前子树 和 是否等于 指定值 */
	
	private int count = 0;
	
	/** 检查树及其所有子树是否是UniValue Tree，是则 + 1，否则返回false。
	 * 先检查其子节点再检查根，是一种从下至上地方法，并且也有 early termination 的概念：若是子树不是
	 * UniValue，则其parent Tree 就不是！  */
	
	private boolean checkCurrentTree(TreeNode root, int val) {
		if (root == null)
			return true;
		
		if (!checkCurrentTree(root.left, root.val) | !checkCurrentTree(root.right, root.val))	// 检查子树是否都是有 root 的值！
			return false;
		
		count++;
		return root.val == val;		// 检查当前值和 root值是否相等
	}

	public int countUnivalSubtrees(TreeNode root) {
		checkCurrentTree(root, 0);
		return count;
	}
	
	
	// --------------------- Recursion -------------------------
	
	public int countUnivalSubtrees2(TreeNode root) {
		int count = 0;
		helper(root, count);
		return count;
	}

	private boolean helper(TreeNode node, int count) {
		if (node == null)
			return true;
		
		boolean left = helper(node.left, count);
		boolean right = helper(node.right, count);
		if (left && right) {
			if (node.left != null && node.val != node.left.val)
				return false;
			
			if (node.right != null && node.val != node.right.val)
				return false;
			
			count++;
			return true;
		}
		return false;
	}
}
