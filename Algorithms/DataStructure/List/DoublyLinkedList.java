package List;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	private static class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> prev;
		
		Node(Item i) {
			item = i;
			next = null;
			prev = null;
		}
	}
	
	public DoublyLinkedList() {
		this.first = null;
		this.last = null;
		n = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return n;
	}
	
	public boolean contains(Item itm) {
		if (isEmpty())	throw new NoSuchElementException();
		for (Item i : this) {
			if (i == itm)
				return true;
		}
		return false;
	}
	
	public void insertFirst(Item itm) {
		Node<Item> oldfirst = first;
		first = new Node<Item>(itm);
		first.next = oldfirst;
		if (oldfirst == null) {
			last = first;
			n++;
			return;
		}
		oldfirst.prev = first;
		n++;
	}
	
	public void insertLast(Item itm) {
		Node<Item> oldlast = last;
		last = new Node<Item>(itm);
		last.prev = oldlast;
		if (oldlast != null)
			oldlast.next = last;
		n++;
	}
	
	public Item delete(Item itm) {
		if (isEmpty() || !contains(itm))	throw new NoSuchElementException();
		Item returnVal;
		Node<Item> curr = first;
		while(curr.item != itm)
			curr = curr.next;
		
		returnVal = curr.item;
		if (first == last) {				//Only one node in the list
			first = null;
			last  = null;
		}
		else if (curr.prev == null)	{		//Delete first node
			first =first.next;
			first.prev = null;
		}
		else if (curr.next == null) {		//Delete last node
			last = last.prev;
			last.next = null;
		}
		else {								//general case, node other than head or tail
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
		}
		
		n--;
		return returnVal;
	}
	
	public void update(Item oldVal, Item newVal) {
		if (!contains(oldVal)) throw new NoSuchElementException();
		Node<Item> curr = first;
		while (curr.item != oldVal)	curr = curr.next;
		curr.item = newVal;
	}
	
	public void traverse() {
		for (Item i : this)
			System.out.print(i + " ");
		System.out.println();
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	public class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		ListIterator(Node<Item> first) {
			current = first;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public Item next() {
			Item rtnVal = current.item;
			current = current.next;
			return rtnVal;
		}
	}
}
