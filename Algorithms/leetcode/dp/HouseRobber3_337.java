package dp;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3_337 {
	
	/** Think naively */
	
	public int rob1(TreeNode root) {
		if (root == null)
			return 0;

		int val = 0;

		if (root.left != null)
			val += rob1(root.left.left) + rob1(root.left.right);

		if (root.right != null)
			val += rob1(root.right.left) + rob1(root.right.right);

		return Math.max(val + root.val, rob1(root.left) + rob1(root.right));
	}
	
	/** memoized solution */
	
	
	public int rob2(TreeNode root) {
		return robSub(root, new HashMap<>());
	}

	private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
		if (root == null)		// base case & corner case
			return 0;
		if (map.containsKey(root))	// check record from memoization table
			return map.get(root);

		int val = 0;

		if (root.left != null)
			val += robSub(root.left.left, map) + robSub(root.left.right, map);

		if (root.right != null)
			val += robSub(root.right.left, map) + robSub(root.right.right, map);

		val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
		
		map.put(root, val);		// put every record into memoization table

		return val;
	}
	
	/**
	 * Now let's take one step back and ask why we have overlapping subproblems.
	 * If you trace all the way back to the beginning, you'll find the answer
	 * lies in the way how we have defined rob(root). As I mentioned, for each
	 * tree root, there are two scenarios: it is robbed or is not. rob(root)
	 * does not distinguish between these two cases, so "information is lost as
	 * the recursion goes deeper and deeper", which results in repeated
	 * subproblems.
	 * 
	 * If we were able to maintain the information about the two scenarios for
	 * each tree root, let's see how it plays out. Redefine rob(root) as a new
	 * function which will return an array of two elements, the first element of
	 * which denotes the maximum amount of money that can be robbed if root is
	 * not robbed, while the second element signifies the maximum amount of
	 * money robbed if it is robbed.
	 * 
	 * Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc. For
	 * the 1st element of rob(root), we only need to sum up the larger elements
	 * of rob(root.left) and rob(root.right), respectively, since root is not
	 * robbed and we are free to rob its left and right subtrees. For the 2nd
	 * element of rob(root), however, we only need to add up the 1st elements of
	 * rob(root.left) and rob(root.right), respectively, plus the value robbed
	 * from root itself, since in this case it's guaranteed that we cannot rob
	 * the nodes of root.left and root.right. */
	
	public int rob3(TreeNode root) {
		int[] res = robSub(root);
		return Math.max(res[0], res[1]);
	}
	
	// First element - root is not robbed, Second element - root is robbed
	private int[] robSub(TreeNode root) {
		if (root == null)
			return new int[2];

		int[] left = robSub(root.left);		// left subtree
		int[] right = robSub(root.right);	// right subtree
		int[] res = new int[2];

		res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);	// root is not robbed
		res[1] = root.val + left[0] + right[0];		// root is robbed, find it's grand-children

		return res;
	}
}
