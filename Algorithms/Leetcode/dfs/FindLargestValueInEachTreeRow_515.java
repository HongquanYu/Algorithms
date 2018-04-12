package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindLargestValueInEachTreeRow_515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res, 0);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res, int d){
        if(root == null){
            return;
        }
       //expand list size
        if(d == res.size()){
            res.add(root.val);
        }
        else{
        //or set value
            res.set(d, Math.max(res.get(d), root.val));
        }
        helper(root.left, res, d+1);
        helper(root.right, res, d+1);
    }
    
    public class TreeNode {
    	     int val;
    	     TreeNode left;
    	     TreeNode right;
    	     TreeNode(int x) { val = x; }
    	}
    
    /*BFS*/
    
	public int[] findValueMostElement(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<Integer> res = new ArrayList<Integer>();
		queue.add(root);
		int queueSize = root == null ? 0 : 1;
		while (queueSize > 0) {
			int largestElement = Integer.MIN_VALUE;
			for (int i = 0; i < queueSize; i++) {
				TreeNode cur = queue.poll();
				largestElement = Math.max(cur.val, largestElement);
				if (cur.left != null)
					queue.add(cur.left);
				if (cur.right != null)
					queue.add(cur.right);
			}
			res.add(largestElement);
			queueSize = queue.size();
		}
		int[] resArray = new int[res.size()];
		for (int i = 0; i < res.size(); i++)
			resArray[i] = res.get(i);
		return resArray;
	}
}
