package segmentTree;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SegmentTree {
	private Node root;
	
	/** 建立一棵线段树 */
	
	public void build(int left, int right) {
		root = new Node(left, right);
		build(root);
	}
	
	private void build(Node root) {
		int left = root.left, right = root.right;
		
		if (right - left == 1) {		// 叶子
			return;
		} else if (right - left > 1) {
			int mid = (left + right) >> 1;	// 将左右区间平分
			
			root.leftChild = new Node(left, mid);
			root.rightChild = new Node(mid, right);
			
			build(root.leftChild);
			build(root.rightChild);
		}
	}

	/** 插入一条线段[c,d] */
	
	public void insert(int begin, int end) {
		insert(begin, end, root);
	}
	
	private void insert(int bgn, int end, Node root) {
		if (root == null || bgn < root.left || end > root.right)
			return;
			
		if (root.left == bgn && root.right == end) {
			root.count++;
			root.covered = true;
			return;
		}
		int mid = (root.left + root.right) >> 1;
		if 		(end <= mid) 	insert(bgn, end, root.leftChild);
		else if 	(bgn >= mid) 	insert(bgn, end, root.rightChild);
		else {
			insert(bgn, mid, root.leftChild);
			insert(mid, end, root.rightChild);
		}
	}

	/** 删除一条线段[c,d] */
	
	public void delete(int bgn, int end) {
		delete(bgn, end, root);
	}
	
	private void delete(int bgn, int end, Node node) {
		if (node == null || bgn < node.left || end > node.right)
			return;
		
		if (bgn == node.left && end == node.right) {
			if (node.count > 0)	node.count--;
			if (node.count == 0) node.covered = false;
			return;
		}
		int mid = (node.left + node.right) >> 1;
		
		if 		(end <= mid) 	delete(bgn, end, node.leftChild);
		else if 	(bgn >= mid) 	delete(bgn, end, node.rightChild);
		else {
			delete(bgn, mid, node.leftChild);
			delete(mid, end, node.rightChild);	// 注意不是mid+1，比如区间[1,10]的左右两部分分别是[1,5]，[5,10]
		}
	}
	
	public List<String> preOrder() { return preOrder(root); }

	private List<String> preOrder(Node root) {
		List<String> res = new ArrayList<>();
		
		res.add(root.toString());
		if (root.right - root.left > 1) {
			res.addAll(preOrder(root.leftChild));
			res.addAll(preOrder(root.rightChild));
		}
		return res;
	}

	/** 统计线段树中covered为true的线段的总长度 */
	public int Count() {
		return Count(root);
	}

	private int Count(Node node) {
		if (node.covered == true)		// 不继续往下查找，否则会重复
			return node.right - node.left;
		else {
			if (node.right - node.left == 1)
				return 0;
			else
				return Count(node.leftChild) + Count(node.rightChild);
		}
	}
}
