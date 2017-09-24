package design;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * HashMap to speedup get and put operation.
 * Store all keys with same frequency in one node, if one of key is hitted, move the key
 * to next node with frequency+1. 
 * When we put new value, we remove least frequency nodes (if needed) and out new node, add a new node
 * to frequency list. and update frequency list
 */

public class LFU_Cache_460 {
    private Node head = null;									// list to manage frequency and sorting nodes with frequency
    private int cap = 0;										// capacity
    private HashMap<Integer, Integer> keyValueMap = null;		// key - value pairs, store data - get, put O(1)
    private HashMap<Integer, Node> keyNodeMap = null;			// key - node pairs, manage data - to connect with frequency
    
    public LFU_Cache_460(int capacity) {
        this.cap  = capacity;
        keyValueMap = new HashMap<Integer, Integer>();
        keyNodeMap  = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {				// CHECK IF KEY EXISTS IN THE LIST
        if (keyValueMap.containsKey(key)) {
            increaseCount(key);
            return keyValueMap.get(key);
        }
        
        return -1;
    }
    
	public void put(int key, int value) {	// ADDING KEY-VALUE PAIR
		if (cap == 0)
			return;
		
		if (keyValueMap.containsKey(key)) {			// exists, update it's value
			keyValueMap.put(key, value);
		} else {
			if (keyValueMap.size() < cap) {			// there's room, add pair
				keyValueMap.put(key, value);
			} else {								// no more room, remove least frequent key
				removeOld();
				keyValueMap.put(key, value);
			}
			
			addToHead(key);							// add node to the front of list
		}
		
		increaseCount(key);							// increase it's frequency
	}
    
    private void addToHead(int key) {		// ADD NODE TO NODE LIST
        if (head == null) {					// no node, use head as first node
            head = new Node(0);
            head.keys.add(key);
        } else if (head.frequency > 0) {		// insert a new node with default frequency
            Node node = new Node(0);
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node;
        } else {								// there's head node with frequency = 0, insert key!
            head.keys.add(key);
        }
        
        keyNodeMap.put(key, head);      		// map key - node, either situation the node is head!
    }
    
	private void increaseCount(int key) {	// MANAGE NODE LIST WITH FREQUENCY, ONCE A QUERY OCCURS
		Node node = keyNodeMap.get(key);
		node.keys.remove(key);								// remove current record to next node

		if (node.next == null) {							// no next node, build a new one
			node.next = new Node(node.frequency + 1);
			node.next.prev = node;
			node.next.keys.add(key);
		} else if (node.next.frequency == node.frequency + 1) {		// 
			node.next.keys.add(key);
		} else {											// add a new node after current key
			Node tmp = new Node(node.frequency + 1);
			tmp.keys.add(key);
			tmp.prev = node;
			tmp.next = node.next;
			node.next.prev = tmp;
			node.next = tmp;
		}

		keyNodeMap.put(key, node.next);
		if (node.keys.size() == 0)							// remove empty nodes
			remove(node);
	}
    
    private void removeOld() {
        if (head == null) return;
        
        int old = 0;
        for (int n: head.keys) {			// get first key in head node - least frequency
            old = n;
            break;
        }
        
		head.keys.remove(old);
		if (head.keys.size() == 0)
			remove(head);
		
		keyNodeMap.remove(old);
		keyValueMap.remove(old);
    }
    
    private void remove(Node node) {		// REMOVE NODE FROM NODE LIST
        if (node.prev == null) {					// first node to remove, move head to next
            head = node.next;
        } else {
            node.prev.next = node.next;
        } 
        if (node.next != null) {					// update pointer of next node
            node.next.prev = node.prev;
        }
    }
    
    class Node {										// define a node
        public int frequency = 0;						// frequency of one key
        public LinkedHashSet<Integer> keys = null;		// to store keys
        public Node prev = null, next = null;			// connections between nodes
        
        public Node(int count) {
            this.frequency = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
}
