package StackQueue;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StackMin_2 {
	
	/* stack 存当前元素和最小值的差，每次初入栈都检查并更新 */
	
	private long min;
	private Stack<Long> stack;

	public StackMin_2() {
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
}
