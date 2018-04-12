package Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		
		Node(Item item) {
			this.item = item;
		}
	}
	
	public Queue() {
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
	
	public Item peek() {
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		return first.item;
	}
	
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>(item);
		last.next = null;
		oldlast.next = last;
		if(isEmpty())   first = last;
		else			oldlast.next = last;
		n++;
	}
	
	public Item dequeue() {
		if(isEmpty()) throw new NoSuchElementException("Queue underflow.");
		Item temp = first.item;
		first = first.next;
		n--;
		if (isEmpty()) last = null;
		return temp;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item i : this) {
			s.append(i);
			s.append('	');
		}
		return s.toString();
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item i = current.item;
			current = current.next;
			return i;
		}
	}
	
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
 }
