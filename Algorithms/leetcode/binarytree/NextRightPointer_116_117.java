package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class NextRightPointer_116_117 {
	static class TreeLinkNode {
		int val;
		
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;
		
		TreeLinkNode(int v) {
			this.val = v;
		}
	}
	
	// ---------------------Iterative: Level traversal, BFS -------------------------
	
    public static void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        if (root == null) return;
        queue.offer(root);
        int levelNodeNumber  = queue.size();
        
        while (!queue.isEmpty()) {
        	
        	while(levelNodeNumber > 0) {	// traversal for one level
        		TreeLinkNode t = queue.poll();
        		t.next = queue.peek();		// pointing to next node of current level
        		if (levelNodeNumber == 1)	// last node of level points null
        			t.next = null;
            	if (t.left != null)		queue.offer(t.left);
            	if (t.right != null)	queue.offer(t.right);
            	levelNodeNumber--;
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
        
        connect(root.left);
        connect(root.right);
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
    	
    	connect(root);
    }
}
