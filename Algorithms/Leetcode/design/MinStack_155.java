package design;

import java.util.Stack;

public class MinStack_155 {
	
	/* The question is ask to construct One stack. So I am using one stack.
	 * 
	 * The idea is to store the gap between the min value and the current value;
	 * 
	 * The problem for my solution is the cast. I have no idea to avoid the cast. Since the possible
	 * gap between the current value and the min value could be Integer.MAX_VALUE-Integer.MIN_VALUE; */
	
	
	private long min;
	private Stack<Long> stack;

	public MinStack_155() {
		stack = new Stack<>();
	}

	public void push(int x) {
		if (stack.isEmpty()) {		// 空栈推0进去
			stack.push(0L);
			min = x;
		} else {
			stack.push(x - min);		// 存的是当前值减去最小值！ Could be negative if min value needs to change
			if (x < min)
				min = x;
		}
	}
	
	// 这里不用返回值， 若要返回，则返回pop + min
	public void pop() {
		if (stack.isEmpty()) return;

		long pop = stack.pop();				// 当前差若是正说明当前值比min大，
		if (pop < 0) 	min = min - pop;		// 弹出负值需要更新最小值min
	}

	public int top() {
		long top = stack.peek();		// 栈顶存的是元素和最小值的差，若是正，那么说明比最小值大
		
		if (top > 0) 	return (int) (top + min);		// 栈顶元素比最小值大
		else 			return (int) (min);				// 栈顶元素就是最小值
	}

	public int getMin() { return (int) min; }
	
	
	/** 
	 * ANOTHER SOLUTION 
	 * */
	
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
	
	/**
	 * */
	
	
	public static void main(String[] args) {
		MinStack_155 test = new MinStack_155();
		
		test.push(-1);
		System.out.print(test.stack.toString() + "				");
		System.out.println(" - Top: " + test.top() + ". Min: " + test.getMin());
		test.push(-5);
		System.out.print(test.stack.toString() + "				");
		System.out.println(" - Top: " + test.top() + ". Min: " + test.getMin());
		test.push(8);
		System.out.print(test.stack.toString() + "			");
		System.out.println(" - Top: " + test.top() + ". Min: " + test.getMin());
		test.push(10);
		System.out.print(test.stack.toString() + "			");
		System.out.println(" - Top: " + test.top() + ". Min: " + test.getMin());
	}
}
