package binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** 449. Serialize and Deserialize BST. Very Similar to 297, serialize binary tree!
 * 
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary search tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless. */

public class SerializeAndDeserializeBST_449 {
	
	/** Pre-order traversal is BST's serialized string. I am doing it iteratively. 
	 * To deserialized it, use a queue to recursively get root node, left subtree and right subtree.
	 * 
	 * I think time complexity is O(NlogN). Worst case complexity should be O(N^2), 
	 * when the tree is really unbalanced. */
	
	private static final String spliter = ",";

	/** Encodes a tree to a single string. */
	
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		if (root == null)
			return null;
		
		// traverse it recursively if you want to, I am doing it iteratively here
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.empty()) {
			root = stack.pop();
			sb.append(root.val).append(spliter);
			
			if (root.right != null)
				stack.push(root.right);
			if (root.left != null)
				stack.push(root.left);
		}
		
		return sb.toString();
	}

    /** Decodes your encoded data to tree. pre-order traversal */
	
	public TreeNode deserialize(String data) {
		if (data.equals(null))
			return null;
		
		Queue<Integer> q = new LinkedList<>();
		
		for (String e : data.split(spliter))
			q.offer(Integer.parseInt(e));
		
		return buildTree(q);
	}
    
    /** 接受一个 queue 装的都是当前树的序列化的前序遍历，来建树！ */
	
	private TreeNode buildTree(Queue<Integer> q) {
		if (q.isEmpty())
			return null;
		
		TreeNode root = new TreeNode(q.poll());
		Queue<Integer> left = new LinkedList<>();
		
		while (!q.isEmpty() && q.peek() < root.val)
			left.offer(q.poll());
		
		root.left = buildTree(left);
		root.right = buildTree(q);
		
		return root;
	}
	
	/** The idea is to encode every non null root value by preorder traversal
	 * 
	 * when deserializing the tree, we can pass by the lower bound and upper
	 * bound to know the boundary of a subtree.
	 * 
	 * This approach has an overhead of looking up one extra number, which would be O ( 2N ) */
	
	public String serialize2(TreeNode root) {
		if (root == null)
			return null;

		String left = serialize2(root.left);
		String right = serialize2(root.right);
		if (left == null && right == null)
			return root.val + "";

		StringBuilder sb = new StringBuilder();
		sb.append(root.val);
		
		if (left != null) 	sb.append(",").append(left);
		if (right != null) 	sb.append(",").append(right);

		return sb.toString();
	}

	public TreeNode deserialize2(String data) {
		if (data == null)
			return null;
		String[] nodes = data.split(",");
		
		return deserialize(nodes, 0, nodes.length - 1);
	}

	private TreeNode deserialize(String[] nodes, int start, int end) {
		if (start > end)
			return null;
		if (start == end)
			return new TreeNode(Integer.valueOf(nodes[start]));

		int leftEnd = start, rightBegin = start + 1;

		TreeNode root = new TreeNode(Integer.valueOf(nodes[start]));
		
		for (int i = start + 1; i <= end; i++) {
			if (Integer.valueOf(nodes[i]) > root.val)
				break;
			leftEnd = i;
			rightBegin = leftEnd + 1;
		}

		root.left = deserialize(nodes, start + 1, leftEnd);
		root.right = deserialize(nodes, rightBegin, end);
		
		return root;
	}
}
