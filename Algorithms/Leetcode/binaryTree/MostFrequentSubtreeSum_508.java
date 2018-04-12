package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  508. Most Frequent Subtree Sum
 * Given the root of a tree, you are asked to find the most frequent subtree
 * sum. The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself). So
 * what is the most frequent subtree sum value? If there is a tie, return all
 * the values with the highest frequency in any order. */

public class MostFrequentSubtreeSum_508 {
	
	/** Idea is post-order traverse the tree and get sum of every sub-tree, put
	 * sum and it's frequency mapping to a HashMap. Then generate result based on the HashMap. */
	
	private Map<Integer, Integer> sumFreq;	// 
	private int maxFreq;

	public int[] findFrequentTreeSum(TreeNode root) {
		maxFreq = 0;
		sumFreq = new HashMap<>();	// sum - count mapping

		postOrderTraversal(root);

		List<Integer> result = new ArrayList<>();
		
		for (int key : sumFreq.keySet())			// get maximum count sums
			if (sumFreq.get(key) == maxFreq)
				result.add(key);
		
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
	
	/** 后序遍历树并计算每一个子树的和 */
	
	private int postOrderTraversal(TreeNode root) {
		if (root == null)
			return 0;

		int left = postOrderTraversal(root.left);
		int right = postOrderTraversal(root.right);

		int sum = left + right + root.val;
		int count = sumFreq.getOrDefault(sum, 0) + 1;
		sumFreq.put(sum, count);

		maxFreq = Math.max(maxFreq, count);		// track maximum count

		return sum;
	}
}
