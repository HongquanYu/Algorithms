package binaryTree;

/**
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called the "root." Besides the root, each house
 * has one and only one parent house. After a tour, the smart thief realized
 * that "all houses in this place forms a binary tree". It will automatically
 * contact the police if two directly-linked houses were broken into on the same
 * night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without
 * alerting the police.
 */

public class HouseRobber3_337 {
	
	/** Think naively */
	
	public int rob(TreeNode root) {
		if (root == null)
			return 0;

		int val = 0;

		if (root.left != null)
			val += rob(root.left.left) + rob(root.left.right);

		if (root.right != null)
			val += rob(root.right.left) + rob(root.right.right);

		return Math.max(val + root.val, rob(root.left) + rob(root.right));
	}
}
