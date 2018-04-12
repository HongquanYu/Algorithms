package binaryTree;

import java.util.LinkedList;
import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Mar 5, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumBinaryTree_654 {
	
	/** 遍历数组， */
	
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		Stack<TreeNode> stack = new Stack<>();
		
		for (int num : nums) {
			TreeNode cur = new TreeNode(num);
			
			while (!stack.isEmpty() && stack.peek().val < cur.val)		// 找到左节点
				cur.left = stack.pop();

			if (!stack.isEmpty())		// 作为右节点
				stack.peek().right = cur;
			
			stack.push(cur);		// [3, ]
		}
		while (stack.size() > 1)	stack.pop();
		
		return stack.peek();
	}
}
