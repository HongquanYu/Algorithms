package binarytree;

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
	 * sum to count mapping to a HashMap. Then generate result based on the HashMap. */
	
	private Map<Integer, Integer> sumToCount;	// 
	private int maxCount;

	public int[] findFrequentTreeSum(TreeNode root) {
		maxCount = 0;
		sumToCount = new HashMap<Integer, Integer>();	// sum - count mapping

		postOrderTraversal(root);

		List<Integer> intermediate = new ArrayList<>();
		
		for (int key : sumToCount.keySet())			// get maximum count sums
			if (sumToCount.get(key) == maxCount)
				intermediate.add(key);
		
		int[] result = new int[intermediate.size()];

		for (int i = 0; i < intermediate.size(); i++)
			result[i] = intermediate.get(i);
		
		return result;
	}

	private int postOrderTraversal(TreeNode root) {
		if (root == null)
			return 0;

		int left = postOrderTraversal(root.left);
		int right = postOrderTraversal(root.right);

		int sum = left + right + root.val;
		int count = sumToCount.getOrDefault(sum, 0) + 1;
		sumToCount.put(sum, count);

		maxCount = Math.max(maxCount, count);		// track maximum count

		return sum;
	}
}
