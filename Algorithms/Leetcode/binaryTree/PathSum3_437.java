package binaryTree;

import java.util.HashMap;
import java.util.Map;

/** 437. Path Sum III
 * 
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000. */

public class PathSum3_437 {
	
	/** So the idea is similar as Two sum, using HashMap to store 
	 * ( key : the prefix sum, value : how many ways get to this prefix sum) , 
	 * and whenever reach a node, we check if prefix sum - target exists in hashmap or not,
	 * if it does, we added up the ways of prefix sum - target into res. 
	 * For instance :  in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, 
	 * let's say we want to find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.
	 * 
	 * I used global variable count, but obviously we can avoid global variable
	 * by passing the count from bottom up. The time complexity is O(n). */
	
	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> prefixSum = new HashMap<>();
		
		prefixSum.put(0, 1);
		
		return helper(root, 0, sum, prefixSum);
	}

	public int helper(TreeNode root, int sum, int target, HashMap<Integer, Integer> prefixSum) {
		if (root == null)
			return 0;

		sum += root.val;
		
		int res = prefixSum.getOrDefault(sum - target, 0);
		prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);

		res += helper(root.left, sum, target, prefixSum) + helper(root.right, sum, target, prefixSum);
		prefixSum.put(sum, prefixSum.get(sum) - 1);
		
		return res;
	}
	
	/** -------------------------------- Recursion - DFS - ----------------------------------
	 *  思想就是对每个节点都进行path 搜索，递归到下层的时候得递减 sum。
	 *  同时又得检查每个节点是不是 path 的起点.
	 *  
	 * Space: O(n) due to recursion.
	 * Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree). */
	
	public int pathSum2(TreeNode root, int sum) {
		if (root == null)
			return 0;
		
		return dfs(root, sum) + pathSum2(root.left, sum) + pathSum2(root.right, sum);
	}
	
	/** 计算当前节点开始有多少个 path 开始 */
	
	private int dfs(TreeNode node, int sum) {
		if (node == null)
			return 0;
		
		return (node.val == sum ? 1 : 0) + dfs(node.left, sum - node.val) + dfs(node.right, sum - node.val);
	}
}
