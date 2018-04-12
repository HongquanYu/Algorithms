package StackQueue;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StackWithMin2 extends Stack<Integer>{	// 这个最小stack是主要数据结构
	Stack<Integer> s2;	// track mins，
	
	public StackWithMin2() {
		s2 = new Stack<>();
	}
	
	public void push(int value) {
		if (value <= min())
			s2.push(value);
		super.push(value);
	}
	
	public Integer pop() {
		int value = super.pop();
		if (value == min())
			s2.pop();
		return value;
	}
	public int min() {
		if (s2.isEmpty())	return Integer.MAX_VALUE;
		else 				return s2.peek();
	}
}
