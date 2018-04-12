package binarySearch;

import binaryTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ClosestBinarySearchTreeValue_270 {
	
	/** 自底向上的递归！首先在最下面找到最小值，然后返回call栈的时候一路更新最小值
	 * 最后到根节点的时候，保留到最小值！ */
	
	public int closestValue(TreeNode root, double target) {
		int a = root.val;
		
		TreeNode kid = target < a ? root.left : root.right;		// 向左走向右走
		
		if (kid == null)		// 到头了
			return a;
		
		int b = closestValue(kid, target);		// 迭代找到子树里最近的那个
		
		return Math.abs(a - target) < Math.abs(b - target) ? a : b;	// 看当前最近还是子树最近
	}
	
	/** 自顶向下的迭代！迭代到最底层的时候找出最小值 */
	
	public int closestValue2(TreeNode root, double target) {
		int ret = root.val;
		
		while (root != null) {
			if (Math.abs(target - root.val) < Math.abs(target - ret))
				ret = root.val;
			
			root = root.val > target ? root.left : root.right;
		}
		
		return ret;
	}
}
