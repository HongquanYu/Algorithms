package design;

import java.util.Stack;

public class MinStack_155 {
	
	/*
	 * The question is ask to construct One stack. So I am using one stack.
	 * 
	 * The idea is to store the gap between the min value and the current value;
	 * 
	 * The problem for my solution is the cast. I have no idea to avoid the cast. Since the possible
	 * gap between the current value and the min value could be Integer.MAX_VALUE-Integer.MIN_VALUE;
	 */
	
	
	long min;
	Stack<Long> stack;

	public MinStack_155() {
		stack = new Stack<>();
	}

	public void push(int x) {
		if (stack.isEmpty()) {
			stack.push(0L);
			min = x;
		} else {
			stack.push(x - min);// Could be negative if min value needs to change
			if (x < min)
				min = x;
		}
	}

	public void pop() {
		if (stack.isEmpty())
			return;

		long pop = stack.pop();

		if (pop < 0)
			min = min - pop;// If negative, increase the min value

	}

	public int top() {
		long top = stack.peek();
		if (top > 0) {
			return (int) (top + min);
		} else {
			return (int) (min);
		}
	}

	public int getMin() {
		return (int) min;
	}
	
	
	/* ANOTHER SOLUTION */
	
	int min2 = Integer.MAX_VALUE;
	Stack<Integer> stack2 = new Stack<Integer>();

	public void push2(int x) {
		// only push the old minimum value when the current
		// minimum value changes after pushing the new value x
		if (x <= min2) {
			stack2.push(min2);
			min2 = x;
		}
		stack2.push(x);
	}

	public void pop2() {
		// if pop operation could result in the changing of the current minimum value,
		// pop twice and change the current minimum value to the last minimum value.
		if (stack2.pop() == min2)
			min = stack2.pop();
	}

	public int top2() {
		return stack2.peek();
	}

	public int getMin2() {
		return min2;
	}
	
}
