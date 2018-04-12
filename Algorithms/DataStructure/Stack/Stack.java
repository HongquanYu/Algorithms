package Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;
	
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		
		Node (Item item) {
			this.item = item;
			next = null;
		}
	}
	
	public Stack() {
		first = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size () {
		return n;
	}
	
	public void push(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>(item);
		first.next = oldfirst;
		n++;
	}
	
	public Item pop() {
		if(isEmpty()) throw new NoSuchElementException("Stack Underflow.");
		Item popedItem = first.item;
		first = first.next;
		n--;
		return popedItem;
	}
	
	public Item peek() {
		if(isEmpty()) throw new NoSuchElementException("Stack underflow.");
		return first.item;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Item i : this) {
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
		
		ListIterator(Node<Item> first) {
			current = first;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item i = current.item;
			current = current.next;
			return i;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				stack.push(item);
			else if (!stack.isEmpty())
				StdOut.print(stack.pop() + " ");
		}
		StdOut.println("(" + stack.size() + " left on stack)");
	}
}
