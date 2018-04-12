package binarySearchTree;

import java.util.HashSet;

/** @author: Hongquan Yu
 *  @date: Mar 5, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TwoSumIV_InputIsABST_653 {
	
	/** dfs Solution. Time: O(n), Space: O(n) */
	
	public boolean findTarget(TreeNode root, int k) {
		HashSet<Integer> set = new HashSet<>();
		
		return dfs(root, set, k);
	}

	public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
		if (root == null)
			return false;
		if (set.contains(k - root.val))
			return true;
		set.add(root.val);
		return dfs(root.left, set, k) || dfs(root.right, set, k);
	}
	
	/** Binary Search Solution. Time: O(nlogn), Space: O(h) */
	
    public boolean findTarget2(TreeNode root, int k) {
        return dfs(root, root,  k);
    }
    
    /**  */
    
    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null)	return false;
        
        return search(root, cur, k - cur.val) || 
        		dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }
    
    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)		return false;
        
        return (root.val == value) && (root != cur) 
            || (root.val < value) && search(root.right, cur, value) 
                || (root.val > value) && search(root.left, cur, value);
    }
}
