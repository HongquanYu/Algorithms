package List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;

	private static class Node<Item> {
		Item item;
		Node<Item> next;
		
		private Node(Item item) {
			this.item = item;
			this.next = null;
		}
		
		private Node() {}
	}
	
	public SinglyLinkedList() {
		first = null;
		n = 0;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return first == null;
	}
	
	public void insert(Item val) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = val;
		first.next = oldfirst;
		n++;
	}

	public Item delete(Item del) {
		if (isEmpty() || (!contains(del)))
			throw new NoSuchElementException();

		Node<Item> prev = new Node<Item>();		//use to references to track the current node and previous one.
		prev.next = first;
		Node<Item> curr = first;
		Item delItem;
		
		while (curr.item != del && curr.next != null) {
			curr = curr.next;
			prev = prev.next;
		}

		delItem = curr.item; 									// update return value
		if (curr == first)				first = first.next; 	// delete head node
		else if (curr.next == null)		curr = null; 			// delete tail node
		else							prev.next = curr.next;  // delete node other than head or tail
		n--;

		return delItem;
	}
	
	public boolean update(Item oldValue, Item newValue) {
		if (!contains(oldValue))		throw new NoSuchElementException();
		Node<Item> curr = first;
		while (curr.item != oldValue)	curr = curr.next;
		curr.item = newValue;
		return true;
	}

	public boolean contains(Item i) {
		Node<Item> r = first;
		while (r != null) {
			if (r.item == i)
				return true;
			r = r.next;
		}
		return false;
	}
	
	//use iterator to traverse the list.
	public boolean contains2(Item i) {
		for (Item n : this) {
			if (n == i)		return true;
		}
		return false;
	}
	
	public void traverse() {
		for (Item n : this)
			System.out.print(n + " ");
		System.out.println();
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item i = current.item;
			current = current.next;
			return i;
		}
	}
}
