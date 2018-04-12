package binarySearchTree;

import java.util.Deque;
import java.util.LinkedList;

public class ConvertSortedArrayToBinarySearchTree_108 {
	
	/**	108. Convert Sorted Array to Binary Search Tree. 
	 * 
	 * Given an array where elements are sorted in ascending order, convert it
	 * to a height balanced BST.
	 * 
	 */
	
    // --------------------- Recursion -------------------------
	
	public TreeNode sortedArrayToBST(int[] nums) {
		return helper(nums, 0, nums.length - 1);
	}

	private TreeNode helper(int[] nums, int lo, int hi) {
		if (lo > hi)
			return null;

		int mid = lo + (hi - lo) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		
		root.left = helper(nums, lo, mid - 1);
		root.right = helper(nums, mid + 1, hi);
		
		return root;
	}
    
	/**
	 * It is very similar to doing a tree inorder traversal, I use three stacks
	 * - nodeStack stores the node I am going to process next
	 * - leftIndexStack and rightIndexStack store the range where this node need
	 * to read from the nums.
	 */
    
    // --------------------- Iterative -------------------------
    
    public TreeNode sortedArrayToBST2(int[] nums) {
        
        int len = nums.length;
        if ( len == 0 ) { return null; }
        
        TreeNode head = new TreeNode(0); 	// 0 as a placeholder
        
        Deque<TreeNode> nodeStack       = new LinkedList<TreeNode>() {{ push(head);  }};
        Deque<Integer>  leftIndexStack  = new LinkedList<Integer>()  {{ push(0);     }};
        Deque<Integer>  rightIndexStack = new LinkedList<Integer>()  {{ push(len-1); }};
        
		while (!nodeStack.isEmpty()) {
			TreeNode currNode = nodeStack.pop();
			
			int l = leftIndexStack.pop();
			int r = rightIndexStack.pop();		// right index
			int mid = l + (r - l) / 2; 			// avoid overflow
			currNode.val = nums[mid];			// build root node
			
			if (l <= mid - 1) {		// 左子树还有节点需要添加
				currNode.left = new TreeNode(0);		// 新建一个节点并进入node stack，值后面添加
				nodeStack.push(currNode.left);
				
				leftIndexStack.push(l);				// 添加一对索引（索引都是成双成对添加），缩小范围到左边
				rightIndexStack.push(mid - 1);
			}
			if (mid + 1 <= r) {		// right index is valid
				currNode.right = new TreeNode(0);
				nodeStack.push(currNode.right);
				
				leftIndexStack.push(mid + 1);
				rightIndexStack.push(r);
			}
		}
        return head;
    }
    
}
