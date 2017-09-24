package binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachLevel_515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)	return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	
        	int size = queue.size();
        	int max = Integer.MIN_VALUE;
        	
        	while (size-- > 0) {
        		TreeNode t = queue.poll();
        		if (t.left != null)		queue.offer(t.left);
        		if (t.right != null) 	queue.offer(t.right);
        		max = Math.max(max, t.val);
        	}
        	
        	res.add(max);
        }
        
        return res;
    }
}
