package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevels_637 {
	
	/** Iterative */
	
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null)
        	return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	int levelSize = queue.size();
            Double levelSum = 0.0;
        	
            int c = levelSize;
        	while (c-- > 0) {
        		TreeNode t = queue.poll();
        		levelSum += t.val;
        		if (t.left != null)		queue.offer(t.left);
        		if (t.right != null)	queue.offer(t.right);
        	}
        	res.add(levelSum / levelSize);
        }
        
        return res;
    }
    
    /** Recursion */
    
    public List<Double> averageOfLevelsR(TreeNode root) {
    	
    	List<Double> res = new LinkedList<>();
    	List<List<Integer>> l = new LinkedList<>();
    	
    	helper(root, 0, res, l);
    	
    	for (List<Integer> p : l) {
    		if (p.size() == 0)
    			continue;
    		
    		Double sum = 0.0;
    		for (Integer i : p) sum += i;
    		res.add(sum / p.size());
    	}
    	
    	return res;
    }
    
    private void helper(TreeNode node, int height, List<Double> res, List<List<Integer>> l) {
    	
    	if (node == null)
    		return;
    	
    	if (height >= res.size())
    		l.add(new LinkedList<>());
    	
    	l.get(height).add(node.val);
    	
    	helper(node.left, height + 1, res, l);
    	helper(node.right, height + 1, res, l);
    }
    
    
    /** Edirotial Solution - DFS */
    
	public List<Double> averageOfLevels2(TreeNode root) {
		List<Integer> count = new ArrayList<>();
		List<Double> res = new ArrayList<>();
		
		average(root, 0, res, count);
		
		for (int i = 0; i < res.size(); i++)
			res.set(i, res.get(i) / count.get(i));
		
		return res;
	}

	public void average(TreeNode t, int i, List<Double> sum, List<Integer> count) {
		
		if (t == null)
			return;
		
		if (i < sum.size()) {
			sum.set(i, sum.get(i) + t.val);
			count.set(i, count.get(i) + 1);
		} else {
			sum.add(1.0 * t.val);
			count.add(1);
		}
		
		average(t.left, i + 1, sum, count);
		average(t.right, i + 1, sum, count);
	}
	
	/** Edirotial Solution - BFS */
	
	public List<Double> averageOfLevels3(TreeNode root) {
		
		List<Double> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		
		queue.add(root);
		
		while (!queue.isEmpty()) {
			
			long sum = 0, count = 0;
			Queue<TreeNode> temp = new LinkedList<>();
			
			while (!queue.isEmpty()) {
				
				TreeNode n = queue.remove();
				sum += n.val;
				count++;
				
				if (n.left != null)
					temp.add(n.left);
				if (n.right != null)
					temp.add(n.right);
			}
			
			queue = temp;
			res.add(sum * 1.0 / count);
		}
		
		return res;
	}
}
