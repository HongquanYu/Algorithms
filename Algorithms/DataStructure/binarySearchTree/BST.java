package binarySearchTree;

import java.util.NoSuchElementException;

import Queue.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int size;
		
		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if (x == null)	return 0;
		else return x.size;
	}
	
	public boolean contains(Key key) {
		if (key == null)	throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if (x == null)	return null;
		int cmp = key.compareTo(x.key);
		if			(cmp < 0)	return get(x.left, key);
		else if		(cmp > 0)	return get(x.right, key);
		else					return x.val;
	}
	
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null)	return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if			(cmp < 0)		x.left = put(x.left, key, val);
		else if 	(cmp > 0)		x.right = put(x.right, key, val);
		else 						x.val = val;
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public void deleteMin() {
		if (isEmpty())	throw new NoSuchElementException("symbol table underflow");
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null)	return x.right; // base case. delete x,
		x.left = deleteMin(x.left);	// the node to receive x.right, which is 
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMax() {
		if (isEmpty())	throw new NoSuchElementException("symbol table underflow");
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null)	return x.left;
		x.right = deleteMax(x.right);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void delete(Key key) {
		if (key == null)	throw new IllegalArgumentException("argument to delete() is null");
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		
		int cmp = key.compareTo(x.key);
		if			(cmp < 0) x.left = delete(x.left, key);
		else if		(cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null)	return x.left;
			if (x.left == null)		return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
	
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    } 

    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 
    
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    } 

    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 
    
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }
}
