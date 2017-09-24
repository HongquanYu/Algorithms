package design;

import java.util.HashMap;

public class LRU_Cache_146 {
	private HashMap<Integer, Integer> keyValue;
	private HashMap<Integer, Node> keyNode;
	private static int size;
	private Node head = null;
	private Node tail = null;
	private int capacity;
	
    public LRU_Cache_146(int capacity) {
    	keyValue = new HashMap<Integer, Integer>();
    	keyNode = new HashMap<Integer, Node>();
        this.capacity = capacity;
        size = 0;
    }
    
    public int get(int key) {
        if (keyValue.containsKey(key)) {
        	moveToHead(key);
        	return keyValue.get(key);
        }
        	
        return -1;
    }
    
    public void put(int key, int value) {
    	if (keyValue.containsKey(key)) {		// If exist key, update value and move to head
    		keyValue.replace(key, value);
    		moveToHead(key);
    		return;
    	}
        if (size == capacity) {		// remove last one
        	removeLast();
        }
        
        keyValue.put(key, value);
        addToHead(key);
    }

    private Node removeLast() {
    	Node t = tail;
    	
    	if (head == tail) {			// only one node in the list
    		tail = null;
    		head = null;
    	} else {					// more than one node in the list
    		tail = tail.prev;
        	tail.next = null;
    	}
    	
    	keyValue.remove(t.hashMapKey);
    	keyNode.remove(t.hashMapKey);
    	
    	size--;
    	
    	return t;
    }

    private void moveToHead(int key) {		// if one node got hit, put it in the front of the list
    	Node node = keyNode.get(key);
    	if (node == head)			// only one node: node = head = tail
    		return;
    	if (node == tail) {			// node is the last node
    		tail = tail.prev;
    		tail.next = null;
    	} else {					// node is in the middle of the list
    		node.prev.next = node.next;
    		node.next.prev = node.prev;
    	}
		node.next = head;
		node.prev = null;
		head.prev = node;
		head = node;
    }
    
    private void addToHead(int key) {
    	if (head == null) {					// no node in the list
    		head = new Node(key);
    		tail = head;
    	} else {							// insert to the 0 position
    		Node t = new Node(key);
    		t.next = head;
    		head.prev = t;
    		head = t;
    	}
    	
    	keyNode.put(key, head);
    	size++;
    }
    
    
    class Node {
    	int hashMapKey;
    	Node next, prev;
    	Node(int key) {
    		this.hashMapKey = key;
    		this.next = null;
    		this.prev = null;
    	}
    }
    
    
//    public static void main(String[] args) {
//    	LRU_Cache_146 nn = new LRU_Cache_146(10);
//    	System.out.println("1, Insery key 10");
//    	nn.put(10, 13);
//    	
//    	System.out.println("2, Insery key 3");
//    	nn.put(3, 17);
//    	
//    	System.out.println("3, Insery key 6");
//    	nn.put(6, 11);
//    	
//    	System.out.println("4, Insery key 10");
//    	nn.put(10, 5);
//    	
//    	System.out.println("5, Insery key 9");
//    	nn.put(9, 10);
//    	
//    	System.out.println("6, Check key 13");
//    	System.out.println(nn.get(13));
//    	
//    	System.out.println("7, Insery key 2");
//    	nn.put(2, 19);
//    	
//    	System.out.println("8, Check key 2");
//    	System.out.println(nn.get(2));
//    	
//    	System.out.println("9, Check key 3");
//    	System.out.println(nn.get(3));
//    	
//    	System.out.println("10, Insery key 5");
//    	nn.put(5, 25);
//    	
//    	System.out.println("11, Check key 8");
//    	System.out.println(nn.get(8));
//    	
//    	System.out.println("12, Insery key 9");
//    	nn.put(9, 22);
//    	
//    	System.out.println("13, Insery key 5");
//    	nn.put(5, 5);
//    	
//    	System.out.println("14, Insery key 1");
//    	nn.put(1, 30);
//    	
//    	System.out.println("15, Check key 11");
//    	System.out.println(nn.get(11));
//    	
//    	System.out.println("16, Insery key 9");
//    	nn.put(9, 12);
//    	
//    	System.out.println("17, Check key 7");
//    	System.out.println(nn.get(7));
//    	
//    	System.out.println("18, Check key 5");
//    	System.out.println(nn.get(5));
//    	
//    	System.out.println("19, Check key 8");
//    	System.out.println(nn.get(8));
//    	
//    	System.out.println("20, Check key 9");
//    	System.out.println(nn.get(9));
//    	
//    	System.out.println("21, Insery key 4");
//    	nn.put(4, 30);
//    	
//    	System.out.println("22, Insery key 9");
//    	nn.put(9, 3);
//    	
//    	System.out.println("23, Check key 9");
//    	System.out.println(nn.get(9));
//    	
//    	System.out.println("24, Check key 10");
//    	System.out.println(nn.get(10));
//    	
//    	System.out.println("25, Check key 10");
//    	System.out.println(nn.get(10));
//    	
//    	System.out.println("26, Insery key 6");
//    	nn.put(6, 14);
//    }
}
