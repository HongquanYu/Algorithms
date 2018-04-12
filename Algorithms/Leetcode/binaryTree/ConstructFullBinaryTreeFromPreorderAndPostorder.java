package binaryTree;

/** @author: Hongquan Yu
 *  @date: Feb 19, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/* The pre and post order of FULL BINARY TREE!
 * Cannot build a tree from pre and post of a regular tree! */

public class ConstructFullBinaryTreeFromPreorderAndPostorder {
	private int preIndex;

	public TreeNode buildTreeRecursion(int pre[], int post[], int l, int h, int size) {
		if (preIndex >= size || l > h)
			return null;

		TreeNode root = new TreeNode(pre[preIndex++]);	// 建立root 并移动指针

		if (l == h || preIndex >= size)		// bounds reached
			return root;
		
		int i = l;
		while (i <= h && post[i] != pre[preIndex])	// 找到 left child of root!
			i++;
		
		// 左右子树递归
		if (i <= h) {
			root.left = buildTreeRecursion(pre, post, l, i, size);			
			root.right = buildTreeRecursion(pre, post, i + 1, h, size);
		}
		
		return root;
	}

	TreeNode buildTree(int pre[], int post[]) {
		if (pre == null || post == null || post.length != pre.length)
			return null;
		int size = pre.length;
		
		preIndex = 0;
		return buildTreeRecursion(pre, post, 0, size - 1, size);
	}

	void printInorder(TreeNode root) {
		if (root == null)
			return;
		printInorder(root.left);
		System.out.print(root.val + " ");
		printInorder(root.right);
	}

	public static void main(String[] args) {

		int pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7};
		int post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
		
		ConstructFullBinaryTreeFromPreorderAndPostorder con = new ConstructFullBinaryTreeFromPreorderAndPostorder();

		TreeNode root = con.buildTree(pre, post);

		System.out.println("Inorder traversal of the constructed tree:");
		con.printInorder(root);
	}
}
