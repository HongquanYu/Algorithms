package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** 270. Closest Binary Search Tree Value
 * 
 * Given a non-empty binary search tree and a target value, find the value in
 * the BST that is closest to the target.
 * 
 * Note: Given target value is a floating point. You are guaranteed to have only
 * one unique value in the BST that is closest to the target. */

public class ClosestValue_270 {
	
	// ----------------------------  Iterative   ----------------------------------
	// in-order traverse the tree and compare each node with target
	
    public int closestValue(TreeNode root, double target) {
    	if (root == null)	return 0;
    	
    	int val = Integer.MAX_VALUE;
    	double dif = Double.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        
        while (cur != null || !stack.isEmpty()) {
        	while(cur != null) {
        		stack.push(cur);
        		cur = cur.left;
        	}
        	
        	cur = stack.pop();
        	if (Math.abs(cur.val - target) < dif) {
        		val = cur.val;
        		dif = Math.abs(cur.val - target);
        	}
        	cur = cur.right;
        }
        return val;
    }
    
    // ----------------------------  Recursion   ----------------------------------
    
	public int closestValue2(TreeNode root, double target) {
		int a = root.val;
		TreeNode kid = target < a ? root.left : root.right;
		if (kid == null)
			return a;
		int b = closestValue(kid, target);
		return Math.abs(a - target) < Math.abs(b - target) ? a : b;
	}
	
	
	/**
	 * 272. Closest Binary Search Tree Value II
	 * 
	 * Given a non-empty binary search tree and a target value, find k values in
	 * the BST that are closest to the target.
	 * 
	 * Note: Given target value is a floating point. You may assume k is always
	 * valid, that is: k ≤ total nodes. You are guaranteed to have only one
	 * unique set of k values in the BST that are closest to the target. Follow
	 * up: Assume that the BST is balanced, could you solve it in less than O(n)
	 * runtime (where n = total nodes)? */
	
	
	/** Solution
	 * 
	 * The idea is to compare the predecessors and successors of the closest
	 * node to the target, we can use two stacks to track the predecessors and
	 * successors, then like what we do in merge sort, we compare and pick the
	 * closest one to the target and put it to the result list.
	 * 
	 * As we know, inorder traversal gives us sorted predecessors, whereas
	 * reverse-inorder traversal gives us sorted successors.
	 * 
	 * We can use iterative inorder traversal rather than recursion, but to keep
	 * the code clean, here is the recursion version. */
	
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> res = new ArrayList<>();

		Stack<Integer> pre = new Stack<>(); 	// predecessors, normally put into stack
		Stack<Integer> suc = new Stack<>(); 	// successors, reversely put into stack

		inorder(root, target, false, pre);		// 
		inorder(root, target, true, suc);		// 

		while (k-- > 0) {				// find k items, merge method
			if (pre.isEmpty())
				res.add(suc.pop());
			else if (suc.isEmpty())
				res.add(pre.pop());
			else if (Math.abs(pre.peek() - target) < Math.abs(suc.peek() - target))
				res.add(pre.pop());
			else
				res.add(suc.pop());
		}

		return res;
	}

	// in-order traversal
	void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
		if (root == null)
			return;

		inorder(reverse ? root.right : root.left, target, reverse, stack);	// Predecessor: left-root-right
		
		// early terminate, no need to traverse the whole tree
		if ((reverse && root.val <= target)				// Successors only store values bigger than target
				|| (!reverse && root.val > target))		// Predecessors only store values less than target
			return;
		
		stack.push(root.val);	// track the value of current node
		
		inorder(reverse ? root.left : root.right, target, reverse, stack);	// Successor: right-root-left
	}
	
	
	/**
	 * Following the hint, I use a predecessor stack and successor stack. I do a
	 * logn traversal to initialize them until I reach the null node. Then I use
	 * the getPredecessor and getSuccessor method to pop k closest nodes and
	 * update the stacks.
	 * 
	 * Time complexity is O(klogn), since k BST traversals are needed and each
	 * is bounded by O(logn) time. Note that it is not O(logn + k) which is the
	 * time complexity for k closest numbers in a linear array.
	 * 
	 * Space complexity is O(klogn), since each traversal brings O(logn) new
	 * nodes to the stack. */
	
	
	public List<Integer> closestKValues2(TreeNode root, double target, int k) {
		
		List<Integer> result = new LinkedList<Integer>();
		Stack<TreeNode> pred = new Stack<TreeNode>();
		Stack<TreeNode> succ = new Stack<TreeNode>();
		TreeNode curr = root;
		
		// populate the predecessor and successor stacks
		while (curr != null) {
			if (target <= curr.val) {
				succ.push(curr);
				curr = curr.left;
			} else {
				pred.push(curr);
				curr = curr.right;
			}
		}
		
		while (k-- > 0) {
			if (pred.empty()) {
				result.add(getSuccessor(succ));
			} else if (succ.empty()) {
				result.add(getPredecessor(pred));
			} else if (Math.abs(target - pred.peek().val) < Math.abs(target - succ.peek().val)) {
				result.add(getPredecessor(pred));
			} else {
				result.add(getSuccessor(succ));
			}
		}
		return result;
	}

	private int getPredecessor(Stack<TreeNode> st) {
		TreeNode popped = st.pop();
		TreeNode p = popped.left;
		while (p != null) {
			st.push(p);
			p = p.right;
		}
		return popped.val;
	}

	private int getSuccessor(Stack<TreeNode> st) {
		TreeNode popped = st.pop();
		TreeNode p = popped.right;
		while (p != null) {
			st.push(p);
			p = p.left;
		}
		return popped.val;
	}
}
