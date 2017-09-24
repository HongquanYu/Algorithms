package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 95. Unique Binary Search Trees II
 *  
 *  Given an integer n, generate all structurally unique BST's (binary search trees)
 *  that store values 1...n. */

public class BuildUniqueBST_95_96 {
	
	/** Use recursion to build tree there must be n nodes in the tree? Every root
	 * differs represents different tree */
	
	public List<TreeNode> generateTrees(int n) {
		
		if (n < 1)
			return new LinkedList<TreeNode>();
		
		return helper(1, n);
	}

	private List<TreeNode> helper(int left, int right) {
		List<TreeNode> list = new LinkedList<TreeNode>();
		if (left > right)
			list.add(null);
		
		for (int rootIndex = left; rootIndex <= right; ++rootIndex) {
			List<TreeNode> l = helper(left, rootIndex - 1);
			List<TreeNode> r = helper(rootIndex + 1, right);

			for (TreeNode ll : l)
				for (TreeNode rr : r) {
					TreeNode root = new TreeNode(rootIndex);
					root.left = ll;
					root.right = rr;
					list.add(root);
				}
		}
		return list;
	}
	
	/** DP solution
	 * 
	 *	G(n): Set of unique BST for a sequence of length n.
	 * 	F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST,
	 *  and the sequence ranges from 1 to n.
	 *  
	 *  G(n) = F(1, n) + F(2, n) + ... + F(n, n).
	 *  F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 
	 *  
	 *  G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) */
	
	public List<TreeNode> generateTreesDP(int n) {

		List<TreeNode>[] result = new List[n + 1];	// subproblem solutions up to i.
		result[0] = new ArrayList<TreeNode>();		// every node represents one unique BST

		if (n == 0)									// corner case
			return result[0];

		result[0].add(null);						// initialize first set to null

		for (int len = 1; len <= n; len++) {		// pointer for root

			result[len] = new ArrayList<TreeNode>();					// initialize a solution set

			for (int j = 0; j < len; j++) {								// traverse subproblems
				for (TreeNode nodeL : result[j]) {						// sub-problems range [0, len-1] as left sub-tree
					for (TreeNode nodeR : result[len - j - 1]) {		// sub-problems range [0, len-1] as right sub-tree

						TreeNode node = new TreeNode(j + 1);	// root of one unique BST, there are len unique BSTs
						node.left = nodeL;						// root.left
						node.right = clone(nodeR, j + 1);		// root.right
						result[len].add(node);					// add to current subproblem solution
					}
				}
			}
		}
		
		return result[n];
	}
	
	/* Why we can clone left sub-tree problem as right one?
	 * 
	 * Because the solutions are symmetric, means number of left subtree equals number of right subtree
	 */
	private TreeNode clone(TreeNode n, int offset) {	// recursively building right sub-tree
		if (n == null)
			return null;

		TreeNode node = new TreeNode(n.val + offset);
		node.left = clone(n.left, offset);
		node.right = clone(n.right, offset);
		return node;
	}
	
	/** 96. Unique Binary Search Trees
	 *  
	 *  Given n, how many structurally unique BST's (binary search trees) that store values 1...n? */
	
	
	/**	 G(n): the number of unique BST for a sequence of length n.
	 * 	F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST,
	 *  and the sequence ranges from 1 to n.
	 *  
	 *  G(n) = F(1, n) + F(2, n) + ... + F(n, n).
	 *  F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 
	 *  
	 *  G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) */
	
	public int numTrees(int n) {
		
		int[] G = new int[n + 1];
		G[0] = G[1] = 1;

		for (int i = 2; i <= n; ++i)
			for (int j = 1; j <= i; ++j)
				G[i] += G[j - 1] * G[i - j];

		return G[n];
	}
}
