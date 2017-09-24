package binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View
 * 
 * Given a binary tree, imagine yourself standing on the right side of it, return
 * the values of the nodes you can see ordered from top to bottom.
 * 
 * For example: Given the following binary tree, 
 * 		 1 		<--- 
 * 		/ \ 
 * 	   2   3 	<--- 
 * 		\   \ 
 * 		 5   4 	<--- 
 * 
 * 	You should return [1, 3, 4].
 */

public class RightSideView_199 {
	
	// --------------------------------Level Order - BFS ------------------------------------------
	
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)	return res;
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	int levelNumber = queue.size();
        	
        	while (levelNumber > 0) {
        		
        		TreeNode t = queue.poll();
        		if (levelNumber == 1)
        			res.add(t.val);
            	
            	if (t.left != null)		queue.offer(t.left);
            	if (t.right != null) 	queue.offer(t.right);
            	levelNumber--;
        	}
        }
        return res;
    }
    
	// --------------------------------Level Order - BFS ------------------------------------------
	
    public List<Integer> rightSideViewRecursion(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(res, root, 0);
        
        return res;
    }
    
    private void helper(List<Integer> res, TreeNode root, int depth) {
    	if (root == null)	return;
    	
    	if (depth == res.size())	// in every level, add only one node
    		res.add(root.val);
    	
    	helper(res, root.right, depth + 1);	// right branch first
    	helper(res, root.left, depth + 1);
    }
}
