package dfs;

import java.util.ArrayList;
import java.util.List;

import binaryTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindLeavesOfBinaryTree_366 {
	
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        height(root, res);
        
        return res;
    }
    
    // 当前树的高度，
	private int height(TreeNode node, List<List<Integer>> res) {
		if (node == null)
			return -1;
		
		// 
		int level = 1 + Math.max(height(node.left, res), height(node.right, res));
		
		if (res.size() < level + 1)	// 结果
			res.add(new ArrayList<>());
		
		res.get(level).add(node.val);
		
		return level;
	}
}
