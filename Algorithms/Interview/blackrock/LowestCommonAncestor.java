package blackrock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.stream.Stream;

/** @author: Hongquan Yu
 *  @date: Mar 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LowestCommonAncestor {

	/**
	 * Iterate through each line of input.
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		String line, input = "";
		while ((line = in.readLine()) != null) {
			input += line;
		}

		// Build tree
		TreeNode root = new TreeNode(30);
		root.left = new TreeNode(8);
		root.right = new TreeNode(52);

		TreeNode secondLeft = root.left;
		secondLeft.left = new TreeNode(3);
		secondLeft.right = new TreeNode(20);

		TreeNode thirdRight = secondLeft.right;
		thirdRight.left = new TreeNode(10);
		thirdRight.right = new TreeNode(29);

		// parse input in main method
		String[] raw = input.trim().split(" ");
		int[] targets = Stream.of(raw).mapToInt(Integer::parseInt).toArray();

		findLCA(targets, root);
	}

	public static void findLCA(int[] targets, TreeNode root) {
		int first = targets[0];
		int second = targets[1];

		TreeNode t = findLCA(root, first, second);

		if (t != null)
			System.out.print(t.value);
	}

	private static TreeNode findLCA(TreeNode root, int q, int p) {
		if (root == null || root.value == q || root.value == p)
			return root;

		TreeNode left = findLCA(root.left, q, p);
		TreeNode right = findLCA(root.right, q, p);

		if (left == null)
			return right;
		else if (right == null)
			return left;
		else
			return root;
	}

	static class TreeNode {
		int value;

		TreeNode left, right;

		public TreeNode(int d) {
			this.value = d;
			this.left = null;
			this.right = null;
		}
	}
}
