package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ConstructStringFromBinaryTree_606 {
	public String tree2str(TreeNode t) {
		if (t == null)
			return "";
		if (t.left == null && t.right == null)
			return t.val + "";
		if (t.right == null)
			return t.val + "(" + tree2str(t.left) + ")";
		return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public String tree2str2(TreeNode t) {
		if (t == null)
			return "";

		String result = t.val + "";

		String left = tree2str(t.left);
		String right = tree2str(t.right);

		if (left == "" && right == "")
			return result;
		if (left == "")
			return result + "()" + "(" + right + ")";
		if (right == "")
			return result + "(" + left + ")";
		return result + "(" + left + ")" + "(" + right + ")";
	}
}
