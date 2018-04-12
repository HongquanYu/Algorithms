package bloomberg;

import java.util.LinkedList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

import binarySearchTree.TreeNode;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BinaryTreeAscendent {
	private List<Integer> res = new LinkedList<>();
	
	public List<Integer> findAscendent(TreeNode root, TreeNode target) {
		ascendent(root, target);
		return res;
	}
	
	private boolean ascendent(TreeNode root, TreeNode target) {
		if (root == null)
			return false;
		
		if (root.val == target.val) {
			res.add(root.val);
			return true;
		}
		boolean left = ascendent(root.left, target);
		boolean right = ascendent(root.right, target);
		if (left || right) {
			res.add(root.val);
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(4);
		root.right = new TreeNode(2);
		TreeNode p = root.left;
		p.left = new TreeNode(5);
		p.right = new TreeNode(3);
		
		BinaryTreeAscendent b = new BinaryTreeAscendent();
		System.out.println(b.findAscendent(root, p.right).toString());
	}
}
