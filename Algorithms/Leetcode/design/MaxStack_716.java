package design;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Feb 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaxStack_716 {
	Stack<Integer> stack;			// 存的是当前元素
	Stack<Integer> maxStack;			// 追踪目前为止的最大元素

	public MaxStack_716() {
		stack = new Stack<>();
		maxStack = new Stack<>();
	}

	public void push(int x) {
		int max = maxStack.isEmpty() ? x : maxStack.peek();
		maxStack.push(max > x ? max : x);
		stack.push(x);
	}

	public int pop() {
		maxStack.pop();
		return stack.pop();
	}

	public int top() {
		return stack.peek();
	}

	public int peekMax() {
		return maxStack.peek();
	}
	
	// 
	public int popMax() {
		int max = peekMax();		// 当前序列里的最大元素
		Stack<Integer> buffer = new Stack();
		
		while (top() != max) 	buffer.push(pop());		// 找到最大值并弹出	
		pop();
		while (!buffer.isEmpty()) 	push(buffer.pop());	// 把栈顶的元素给还回去
		
		return max;
	}
}
