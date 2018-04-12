package design;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues_225 {
	private Queue<Integer> input = new LinkedList<>();
	private Queue<Integer> output = new LinkedList<>();
	private int top;

	// Push element x onto stack.
	public void push(int x) {
		input.add(x);
		top = x;
	}
	
	// 弹出元素的时候，将
	public int pop() {
		while (input.size() > 1) {
			top = input.remove();
			output.add(top);
		}
		input.remove();
		Queue<Integer> temp = input;
		input = output;
		output = temp;
		
		return top;
	}
	
	public int top() {
		return top;
	}
	
	public boolean empty() {
		return input.isEmpty() && output.isEmpty();
	}
}
