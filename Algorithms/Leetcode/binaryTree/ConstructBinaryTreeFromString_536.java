package binaryTree;

import java.util.Stack;

/** 536. Construct Binary Tree from String
 * 
 * You need to construct a binary tree from a string consisting of parenthesis
 * and integers.
 * 
 * The whole input represents a binary tree. It contains an integer followed by
 * zero, one or two pairs of parenthesis. The integer represents the root's
 * value and a pair of parenthesis contains a child binary tree with the same
 * structure.
 * 
 * You always start to construct the left child node of the parent first if it
 * exists.
 * 
 * Note: 
 * 	There will only be '(', ')', '-' and '0' ~ '9' in the input string. 
 * 	An empty tree is represented by "" instead of "()". 
 * 
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *
 *     	4
 *  	   /  \
 * 	  2    6
 * 	 / \   / 
 *  3   1 5  */

public class ConstructBinaryTreeFromString_536 {
	
	/** Intuition 是迭代的建树！
	 * 当找到一个左右括号平衡的点就是一个子树区间，进行递归！
	 * 左子树从当前节点开始，而右子树必须要不是当前节点 */
	
	public TreeNode str2tree(String s) {
		if (s == null || s.length() == 0)
			return null;
		
		int firstParen = s.indexOf("(");
		int rootVal = firstParen == -1 ? Integer.parseInt(s) : Integer.parseInt(s.substring(0, firstParen));
		TreeNode root = new TreeNode(rootVal);
		
		if (firstParen == -1)		// 只有一个节点，直接返回 root
			return root;
		
		// 遍历剩下的数组，并且
		int start = firstParen, leftSubTree = 0;
		for (int i = start; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				leftSubTree++;
			else if (s.charAt(i) == ')')
				leftSubTree--;
			
			// leftSubTree == 0 代表着其有一段子树区间
			if (leftSubTree == 0) {
				if (start == firstParen) {	// 从根节点开始的区间为左子树
					root.left = str2tree(s.substring(start + 1, i));
					start = i + 1;
				} else						// 从后面开始的区间为右子树
					root.right = str2tree(s.substring(start + 1, i));
			}
		}
		
		return root;
	}
	
	/** 也可以使用一个 栈来存当前建立的节点们 */
	
	public TreeNode str2tree2(String s) {
		Stack<TreeNode> stack = new Stack<>();
		int N = s.length();
		
		// 遍历字符串
		for (int i = 0, j = i; i < N; i++, j = i) {
			char c = s.charAt(i);
			if (c == ')')
				stack.pop();
			else if (c >= '0' && c <= '9' || c == '-') {
				while (i + 1 < N && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')		// 得到一个数的区间
					i++;
				TreeNode current = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
				
				if (!stack.isEmpty()) {
					TreeNode parent = stack.peek();
					
					if (parent.left != null)
						parent.right = current;
					else
						parent.left = current;
				}
				stack.push(current);
			}
		}
		
		return stack.isEmpty() ? null : stack.peek();
	}
}
