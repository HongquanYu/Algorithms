package binarySearchTree;

import java.util.Stack;

/** 255. Verify Pre-order Sequence in Binary Search Tree
 * 
 * Given an array of numbers, verify whether it is the correct preorder
 * traversal sequence of a binary search tree.
 * 
 * You may assume each number in the sequence is unique. */

public class VerifyPreorderSequenceInBinarySearchTree_255 {	

	/** 解法利用了一个事实：在BST中根节点大于左子树所有节点，小于右子树所有节点。
	 * 由于先序遍历是 根 -> 左 -> 右。所以当前节点是根，然后将所有的小于当前节点的nodes
	 * 都存在栈里，遇到大于根的节点就说明到达了右子树了。
	 * 我们判断的标准就是：右子树的第一个节点要大于左子树的最后一个节点，也就是 */

	public boolean verifyPreorder(int[] preorder) {
		
		int low = Integer.MIN_VALUE;
		Stack<Integer> path = new Stack<>();		// 追踪左子树的节点
		
		for (int p : preorder) {
			if (p < low)			// 后面的遍历过程中都不能遇到一个比最小值还小的节点！
				return false;
			while (!path.empty() && p > path.peek())		// 找到右子树第一个节点的时候更新最小值为左子树的最大值！
				low = path.pop();
			path.push(p);
		}
		
		return true;
	}
	
	/** Follow up: Could you do it using only constant space complexity? 
	 * 那么就按照上面的解法做一个常数版本的解法：
	 * 我们通过修改 preorder 的特定值来进行判定，但是修改不能影响遍历。
	 * 我们通过将每遍历一个数都存在它的前一个位置这样来保证我们能追踪到左子树
	 * 的最大值，从而比较当前的值是不能小于左子树的最大值的！ */
	
	public boolean verifyPreorder1(int[] preorder) {
		int low = Integer.MIN_VALUE;
		int ptr = -1;			// 当前遍历指针的前一个数，也就是追踪左子树的最大值
		
		for (int p : preorder) {
			if (p < low)		return false;
			
			while (ptr >= 0 && p > preorder[ptr])	// 
				low = preorder[ptr--];
			
			preorder[++ptr] = p;
		}
		
		return true;
	}
	
	/** Another Solution! */
	
	public boolean verifyPreorder2(int[] preorder) {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> inorder = new Stack<>();

		for (int v : preorder) {
			if (!inorder.isEmpty() && v < inorder.peek())
				return false;
			while (!stack.isEmpty() && v > stack.peek()) {
				inorder.push(stack.pop());
			}
			stack.push(v);
		}
		
		return true;
	}
}
