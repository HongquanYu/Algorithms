package binaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 623. Add One Row to Tree
 * 
 * Given the root of a binary tree, then value v and depth d, you need to add a
 * row of nodes with value v at the given depth d. The root node is at depth 1.
 */

public class AddOneRowToTree_623 {
	
	/** Using Recursion(DFS) */
	
	public TreeNode addOneRow(TreeNode t, int v, int d) {
        if (d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = t;
            return n;
        }
        insert(v, t, 1, d);
        return t;
    }

    public void insert(int val, TreeNode node, int depth, int n) {
        if (node == null)
            return;
        
        if (depth == n - 1) {		// 要插入的层的上一层了，新建一个层插入进去
            TreeNode t = node.left;
            node.left = new TreeNode(val);
            node.left.left = t;
            t = node.right;
            node.right = new TreeNode(val);
            node.right.right = t;
        } else {
            insert(val, node.left, depth + 1, n);
            insert(val, node.right, depth + 1, n);
        }
    }
    
    /** Using stack(DFS) */
    
	private class Node {
		Node(TreeNode n, int d) {
			node = n;
			depth = d;
		}

		TreeNode node;
		int depth;
	}

	public TreeNode addOneRow2(TreeNode t, int v, int d) {
		if (d == 1) {
			TreeNode n = new TreeNode(v);
			n.left = t;
			return n;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(new Node(t, 1));
		while (!stack.isEmpty()) {
			Node n = stack.pop();
			if (n.node == null)
				continue;
			if (n.depth == d - 1) {
				TreeNode temp = n.node.left;
				n.node.left = new TreeNode(v);
				n.node.left.left = temp;
				temp = n.node.right;
				n.node.right = new TreeNode(v);
				n.node.right.right = temp;

			} else {
				stack.push(new Node(n.node.left, n.depth + 1));
				stack.push(new Node(n.node.right, n.depth + 1));
			}
		}
		return t;
	}
	
	/** Using queue(BFS) */
	
	public TreeNode addOneRow3(TreeNode t, int v, int d) {
		if (d == 1) {
			TreeNode n = new TreeNode(v);
			n.left = t;
			return n;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(t);
		int depth = 1;
		while (depth < d - 1) {
			Queue<TreeNode> temp = new LinkedList<>();
			while (!queue.isEmpty()) {
				TreeNode node = queue.remove();
				if (node.left != null)
					temp.add(node.left);
				if (node.right != null)
					temp.add(node.right);
			}
			queue = temp;
			depth++;
		}
		while (!queue.isEmpty()) {
			TreeNode node = queue.remove();
			TreeNode temp = node.left;
			node.left = new TreeNode(v);
			node.left.left = temp;
			temp = node.right;
			node.right = new TreeNode(v);
			node.right.right = temp;
		}
		return t;
	}
	
	
	
}
