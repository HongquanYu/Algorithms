package bloomberg.designStackWithMode;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ModeStack {
	Stack<Integer> stack = new Stack<>();		   		 	// Values
	Stack<Integer> modeStack = new Stack<>();				// modes of current stack
	HashMap<Integer, Integer> map = new HashMap<>();			// value - freq
	
	public void push(int x) {
		stack.push(x);
		int freq = map.getOrDefault(x, 0);
		map.put(x, freq + 1);
		if (modeStack.isEmpty() || freq + 1 >= map.get(modeStack.peek()))
			modeStack.push(x);
		
		System.out.print("Stack: ");
		System.out.println(stack);
		System.out.print("ModeStack: ");
		System.out.println(modeStack);
	}
	
	public int pop() {
		if (!stack.isEmpty()) {
			int poped = stack.pop();
			map.put(poped, map.get(poped) - 1);
			int freq = map.get(poped);
			int modeTop = modeStack.pop();
			
			if (!modeStack.isEmpty() && freq > map.get(modeStack.peek()))
				modeStack.push(modeTop);
			
			System.out.print("Stack: ");
			System.out.println(stack);
			System.out.print("ModeStack: ");
			System.out.println(modeStack);
			
			return poped;
		}
		throw new EmptyStackException();
	}
	
	public int getMode() {
		System.out.println("Mode: " + modeStack.peek());
		return modeStack.peek();
	}
	
	public static void main(String[] args) {
		ModeStack m = new ModeStack();
		
		m.push(3);
		m.getMode();
		System.out.println("-------------");
		m.push(2);
		m.getMode();
		System.out.println("-------------");
		m.push(3);
		m.getMode();
		System.out.println("-------------");
		
		m.push(4);;
		m.getMode();
		System.out.println("-------------");
		m.push(4);;
		m.getMode();
		System.out.println("-------------");
		m.pop();
		m.getMode();
		System.out.println("-------------");
		m.push(2);;
		m.getMode();
		System.out.println("-------------");

		m.getMode();
		System.out.println("-------------");
		m.push(3);
		m.getMode();
		System.out.println("-------------");
	}
}
