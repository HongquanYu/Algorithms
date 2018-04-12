package StackQueue;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class QueueViaStacks_4<T> {
	Stack<T> stackNewest, stackOldest;
	
	public QueueViaStacks_4() {
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}
	
	public int size() {
		return stackNewest.size() + stackOldest.size();
	}
	
	public void add(T value) {
		// Push onto stack1
		stackNewest.push(value);
	}
	
	/* Move elements from stackNewest into stackOldest. 
	 * This is usually done so that we can do operations on stackOldest. */
	
	private void shiftStacks() {
		if (stackOldest.isEmpty()) { 
			while (!stackNewest.isEmpty()) {
				stackOldest.push(stackNewest.pop());
			}
		}
	}
	
	public T peek() {
		shiftStacks();
		return stackOldest.peek(); // retrieve the oldest item.
	}
	
	public T remove() {
		shiftStacks();
		return stackOldest.pop(); // pop the oldest item.
	}
}
