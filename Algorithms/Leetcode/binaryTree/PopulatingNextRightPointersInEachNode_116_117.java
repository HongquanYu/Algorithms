package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode_116_117 {
	static class TreeLinkNode {
		int val;
		
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;
		
		TreeLinkNode(int v) {
			this.val = v;
		}
	}
	
	/** ---------------------Iterative: Level traversal, BFS -------------------------
	 * this solution works for 116 and 117 */
	
	public void connect(TreeLinkNode root) {
		Queue<TreeLinkNode> queue = new LinkedList<>();
		if (root == null)
			return;
		queue.offer(root);

		int levelNodeNumber = queue.size();

		while (!queue.isEmpty()) {
			while (levelNodeNumber-- > 0) { // traversal for one level
				TreeLinkNode t = queue.poll();
				t.next = queue.peek(); // pointing to next node of current level
				if (levelNodeNumber == 0) // last node of level points null
					t.next = null;
				if (t.left != null)
					queue.offer(t.left);
				if (t.right != null)
					queue.offer(t.right);
			}

			levelNodeNumber = queue.size();
		}
	}
    
    // --------------------- Recursion -------------------------
    
    public void connect2(TreeLinkNode root) {
        if(root == null)
            return;
            
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }
        
        connect2(root.left);
        connect2(root.right);
    }
    
    /* 117 */
	public void connect_117(TreeLinkNode root) {
		TreeLinkNode head = root;	// The left most node in the lower level
		TreeLinkNode prev = null;	// The previous node in the lower level
		TreeLinkNode curr = null;	// The current node in the upper level
		while (head != null) {
			curr = head;
			prev = null;
			head = null;
			while (curr != null) {
				if (curr.left != null) {
					if (prev != null) 	prev.next = curr.left;
					else 				head = curr.left;
					prev = curr.left;
				}
				if (curr.right != null) {
					if (prev != null) 	prev.next = curr.right;
					else 				head = curr.right;
					prev = curr.right;
				}
				curr = curr.next;
			}
		}
	}
	
	public void connect_117_2(TreeLinkNode root) {
		TreeLinkNode rowHead = new TreeLinkNode(0);	// 新建一个假节点，指向每一行第一个node
		TreeLinkNode ptr = root;		// 当前level的遍历指针

		while (ptr != null) {
			TreeLinkNode levelPtr = rowHead;		// levelPtr 指向每一行的当前节点，指向ptr的下一个节点
			
			while (ptr != null) {		// 遍历当前level至null
				if (ptr.left != null) {		// 当前node首先
					levelPtr.next = ptr.left;		// 在这里将rowHead更新到下一行的第一个node
					levelPtr = levelPtr.next;
				}
				if (ptr.right != null) {
					levelPtr.next = ptr.right;
					levelPtr = levelPtr.next;
				}
				ptr = ptr.next;
			}
			
			// level遍历完成，继续下一层
			ptr = rowHead.next;		// ptr 移到下一层的第一个rowHead节点
			rowHead.next = null;		// 断开rowHead和这一层首节点的链接，为下一层的遍历做准备
		}
	}
    
    // --------------------- High Voted two pointer solution -------------------------
    public static void connect3(TreeLinkNode root) {
	    	if (root == null)	return;
	    	TreeLinkNode pre = root;
	    	TreeLinkNode cur = null;
	    	
	    	while (pre.left != null) {	// If there's a next level
	    		cur = pre;
	    		
	    		while (cur != null) {	// There is no need to process first level
	    			cur.left.next = cur.right;	// left child -> right child
	    			if (cur.next != null)		// right child -> left child of next node
	    				cur.right.next = cur.next.left;
	    			cur = cur.next;				// move to next node
	    		}
	    		pre = pre.left;		// move to next level
	    	}
    }
    
	public static void main(String[] args) {
		TreeLinkNode sub1 = new TreeLinkNode(1);
		sub1.left = new TreeLinkNode(3);
		sub1.right = new TreeLinkNode(4);

		TreeLinkNode sub2 = new TreeLinkNode(2);
		sub2.left = new TreeLinkNode(5);
		sub2.right = new TreeLinkNode(6);

		TreeLinkNode root = new TreeLinkNode(0);
		root.left = sub1;
		root.right = sub2;

		connect3(root);
	}
}
