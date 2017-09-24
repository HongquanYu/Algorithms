package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/** 513. Find Bottom Left Tree Value
 * 
 * Given a binary tree, find the leftmost value in the last row of the tree. 
 * 
 * Note: You may assume the tree (i.e., the given root node) is not NULL. */

public class FindBottomLeftTreeValue_513 {
	
	/** --------------------- Iterative BFS - Level traversal------------------------- */
	
    public int findBottomLeftValue(TreeNode root) {
    	
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int lowLeft = root.val;
        
        while (!queue.isEmpty()) {
        	
        	int levelSize = queue.size();
        	lowLeft = queue.peek().val;
        	 
        	while (levelSize-- > 0) {
        		TreeNode t = queue.poll();
        		
        		if (t.left != null)		queue.offer(t.left);
        		if (t.right != null)	queue.offer(t.right);
        	}
        }
        
        return lowLeft;
    }
    
    
	/** ----------------------------------- Iterative BFS --------------------------------------
	 * Doing BFS right-to-left means we can simply return the last node's value
	 * and don't have to keep track of the first node in the current row or even
	 * care about rows at all.
	 */
    
	public int findBottomLeftValue2(TreeNode root) {
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			
			root = queue.poll();
			
			if (root.right != null)
				queue.add(root.right);
			
			if (root.left != null)
				queue.add(root.left);
		}
		
		return root.val;
	}
	
	// -------------------------------------  Recursion ------------------------------------------
	
	public int findBottomLeftValue3(TreeNode root) {
		return findBottomLeftValue(root, 1, new int[] { 0, 0 });
	}

	public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
		if (res[1] < depth) {	// Found a new level, abandon previous values by updating new ones
			res[0] = root.val;		// record value of current node
			res[1] = depth;			// record depth
		}
		
		if (root.left != null)
			findBottomLeftValue(root.left, depth + 1, res);
		
		if (root.right != null)
			findBottomLeftValue(root.right, depth + 1, res);
		
		return res[0];
	}
}
