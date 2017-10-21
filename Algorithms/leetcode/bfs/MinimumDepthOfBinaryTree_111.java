package bfs;

import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumDepthOfBinaryTree_111 {
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		int depth = 1;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode temp,magic = new TreeNode(0);
		queue.add(root);
		queue.add(magic);
		while(!queue.isEmpty()){
			temp = queue.poll();
			if(temp.equals(magic)){
			    if(!queue.isEmpty()){
			        depth++;
				    queue.add(magic);
			    }
			    continue;
			}
			if(temp.left == null && temp.right == null)
				return depth;
			if(temp.left != null)
				queue.add(temp.left);
			if(temp.right != null)
				queue.add(temp.right);
		}
		return depth;
	}
	
	 public class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
	 }
}
