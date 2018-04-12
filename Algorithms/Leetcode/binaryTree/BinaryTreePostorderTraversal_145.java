package binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal_145 {
	
    /** Iterative Traversal: time N, Space 1 */
	
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		if (root == null) return ans;
		
		stack.push(root);
		while (!stack.isEmpty()) {		// 访问顺序：  right -> left -> root
			TreeNode t = stack.pop();
			ans.addFirst(t.val);		// 从头添加
			
			if (t.left != null)			// 先左后右，由于是反向添加，所有到时候正着数是 left -> right -> root
				stack.push(t.left);
			if (t.right != null)
				stack.push(t.right);
		}
		return ans;
	}
	
    /** Iterative Traversal: time N, Space 1 */
	
	public List<Integer> postorderTraversal2(TreeNode root) {
		LinkedList<Integer> ans = new LinkedList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		
		TreeNode lastVisitedNode = null;
		
		while(!s.isEmpty() || root != null) {
			if (root != null) {		// 找到左子树的底层左节点
				s.push(root);
				root = root.left;
			} else {					// 
				TreeNode tmp = s.peek();
				if (tmp.right != null && lastVisitedNode != tmp)		// 第二个条件，已经访问过top，不能再访问其子节点
					root = tmp.right;
				else {
					ans.add(tmp.val);
					lastVisitedNode = s.pop();
				}
			}
		}
		
		return ans;
	}
	
    /** Recursion Traversal: time N, Space 1 */
	
    public List<Integer> postorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursion(root, res);
        return res;
    }
    
    private void recursion(TreeNode root, List<Integer> res) {
        if (root == null)   return;
        
        recursion(root.left, res);
        recursion(root.right, res);
        res.add(root.val);
    }
    
    /** 可以使用两个栈来简化访问 */
}
