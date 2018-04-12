package StackQueue;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StackMin_2_2 extends Stack<NodeWithMin>{
	public void push(int value) {
		int newMin = Math.min(value, min());
		super.push(new NodeWithMin(value, newMin));
	}
	
	public int min() {
		return this.isEmpty() ? Integer.MAX_VALUE : peek().min;
	}
}
