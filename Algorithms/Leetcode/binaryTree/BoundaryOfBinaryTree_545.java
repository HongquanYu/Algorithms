package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** 545. Boundary of Binary Tree
 * 
 * Given a binary tree, return the values of its boundary in anti-clockwise
 * direction starting from root. Boundary includes left boundary, leaves, and
 * right boundary in order without duplicate nodes. */

public class BoundaryOfBinaryTree_545 {
	
	/** Editorial solution 1. */
	
	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null) 	return res;
		
		if (!isLeaf(root))  res.add(root.val);
		
		// 添加左边界的所有节点
		TreeNode t = root.left;
		while (t != null) {
			if (!isLeaf(t)) 		res.add(t.val);
			
			if (t.left != null) t = t.left;
			else 				t = t.right;
		}
		
		addLeaves(res, root);		// 添加叶子节点
		
		// 添加右边节点，右边要从底层到root逆序添加，所以使用 stack 来帮助
		Stack<Integer> s = new Stack<>();
		t = root.right;
		
		while (t != null) {
			if (!isLeaf(t)) 			s.push(t.val);
			
			if (t.right != null)		t = t.right;
			else 					t = t.left;
		}
		
		while (!s.empty()) 	res.add(s.pop());
		
		return res;
	}
	
	public boolean isLeaf(TreeNode t) {
		return t.left == null && t.right == null;
	}

	public void addLeaves(List<Integer> res, TreeNode root) {
		if (isLeaf(root)) {
			res.add(root.val);
		} else {
			if (root.left != null) 	addLeaves(res, root.left);
			if (root.right != null) 	addLeaves(res, root.right);
		}
	}
	
	
	/** Editorial solution 2. */
	// ------------------------------ Pre-order solution -------------------------------------
	
	public List<Integer> boundaryOfBinaryTree2(TreeNode root) {
		List<Integer> left_boundary = new LinkedList<>(), right_boundary = new LinkedList<>(),
				leaves = new LinkedList<>();
		preorder(root, left_boundary, right_boundary, leaves, 0);
		left_boundary.addAll(leaves);
		left_boundary.addAll(right_boundary);
		return left_boundary;
	}

	public boolean isLeaf2(TreeNode cur) {
		return (cur.left == null && cur.right == null);
	}

	public boolean isRightBoundary(int flag) {
		return (flag == 2);
	}

	public boolean isLeftBoundary(int flag) {
		return (flag == 1);
	}

	public boolean isRoot(int flag) {
		return (flag == 0);
	}

	public int leftChildFlag(TreeNode cur, int flag) {
		if (isLeftBoundary(flag) || isRoot(flag))
			return 1;
		else if (isRightBoundary(flag) && cur.right == null)
			return 2;
		else
			return 3;
	}

	public int rightChildFlag(TreeNode cur, int flag) {
		if (isRightBoundary(flag) || isRoot(flag))
			return 2;
		else if (isLeftBoundary(flag) && cur.left == null)
			return 1;
		else
			return 3;
	}

	public void preorder(TreeNode cur, List<Integer> left_boundary, List<Integer> right_boundary, List<Integer> leaves,
			int flag) {
		if (cur == null)
			return;
		if (isRightBoundary(flag))
			right_boundary.add(0, cur.val);
		else if (isLeftBoundary(flag) || isRoot(flag))
			left_boundary.add(cur.val);
		else if (isLeaf2(cur))
			leaves.add(cur.val);
		preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
		preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
	}
	
	/** solution 3. */
	
	List<Integer> nodes = new ArrayList<>(1000);

	public List<Integer> boundaryOfBinaryTree3(TreeNode root) {

		if (root == null)
			return nodes;

		nodes.add(root.val);
		leftBoundary(root.left);
		leaves(root.left);
		leaves(root.right);
		rightBoundary(root.right);

		return nodes;
	}

	public void leftBoundary(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return;
		nodes.add(root.val);
		if (root.left == null)
			leftBoundary(root.right);
		else
			leftBoundary(root.left);
	}

	public void rightBoundary(TreeNode root) {
		if (root == null || (root.right == null && root.left == null))
			return;
		if (root.right == null)
			rightBoundary(root.left);
		else
			rightBoundary(root.right);
		nodes.add(root.val); // add after child visit(reverse)
	}

	public void leaves(TreeNode root) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			nodes.add(root.val);
			return;
		}
		leaves(root.left);
		leaves(root.right);
	}
	
	/** solution 4.
	 * We perform a single preorder traversal of the tree, keeping tracking of
	 * the left boundary and middle leaf nodes and the right boundary nodes in
	 * the process. A single flag is used to designate the type of node during
	 * the preorder traversal. 
	 * Its values are: 
	 * 	0 - root, 
	 * 	1 - left boundary node,
	 * 	2 - right boundary node, 
	 * 	3 - middle node. */
	
	public List<Integer> boundaryOfBinaryTree4(TreeNode root) {
		List<Integer> left = new LinkedList<>();
		List<Integer> right = new LinkedList<>();
		
		preorder(root, left, right, 0);
		left.addAll(right);
		
		return left;
	}

	public void preorder(TreeNode cur, List<Integer> left, List<Integer> right, int flag) {
		if (cur == null)
			return;
		if (flag == 2)		// 右边界直接加到right里面
			right.add(0, cur.val);
		else if (flag <= 1 || cur.left == null && cur.right == null)		// 叶子加到左边 list 的后面
			left.add(cur.val);
		
		// 向左边递归
		if (flag <= 1)
			preorder(cur.left, left, right, 1);
		else if (flag == 2 && cur.right == null)
			preorder(cur.left, left, right, 2);
		else 
			preorder(cur.left, left, right, 3);
		
		// 向右边递归
		if (flag % 2 == 0)
			preorder(cur.right, left, right, 2);
		else if (flag == 1 && cur.left == null)
			preorder(cur.right, left, right, 1);
		else
			preorder(cur.right, left, right, 3);
	}
}
