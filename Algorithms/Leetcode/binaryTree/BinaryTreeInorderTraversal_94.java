package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal_94 {
	
    /** Iterative Traversal: time N, Space 1 */
	
	public List<Integer> inorderTraversalIterative(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode pointer = root;

		while (!s.isEmpty() || pointer != null) {
			while (pointer != null) {
				s.push(pointer);
				pointer = pointer.left;
			}
			pointer = s.pop();
			res.add(pointer.val);
			pointer = pointer.right;
		}
		/* Another version 
        while(ptr != null || !stack.isEmpty()) {
            if (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            } else {
                TreeNode t = stack.pop();
                res.add(t.val);
                ptr = t.right;
            }
        }	*/

		return res;
	}
	
    /** Recursion Traversal: time N, Space 1 */
    
    public List<Integer> inorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
        		return res;
        
        res.addAll(inorderTraversalRecursion(root.left));
        res.add(root.val);
        res.addAll(inorderTraversalRecursion(root.right));
        
        return res;
    }
    
    /** Morris Traversal: time N, Space 1 */
    
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		TreeNode curr = root, pre;
		
		// 由于没有栈的辅助，只能通过修改指针的方式来遍历
		while (curr != null) {
			if (curr.left == null) {		// 左子树为空，访问当前节点并转向右子树
				res.add(curr.val);
				curr = curr.right;
			} else {						// 左子树不为空，找到左孩子的最右节点
				pre = curr.left;
				while (pre.right != null) pre = pre.right;		// find rightmost node
				
				pre.right = curr; 		// 更新pre的右孩子到当前节点，以保证能向栈一样能返回
				TreeNode temp = curr;
				curr = curr.left; 		// 当前指针向左移向左子树
				temp.left = null; 		// original cur left be null, avoid infinite loops
			}
		}
		
		return res;
	}
}
