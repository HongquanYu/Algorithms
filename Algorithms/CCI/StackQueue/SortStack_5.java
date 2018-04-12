package StackQueue;

import java.util.Stack;

import CtCILibrary.AssortedMethods;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SortStack_5 {
	
	/* Solution one: Rudimentary Merge Sort! */
	
	// 采用mergesort的方法来对栈排序
	public static Stack<Integer> mergesort(Stack<Integer> inStack) {
		if (inStack.size() <= 1)			// base case：只剩一个或者没有元素就直接返回，不需要排序
			return inStack;

		Stack<Integer> left = new Stack<Integer>();		// 左右分堆
		Stack<Integer> right = new Stack<Integer>();		// 
		
		int count = 0;
		while (inStack.size() != 0) {	// 这里进行分堆也没什么特定算法，就只是单双数
			count++;
			if (count % 2 == 0)	left.push(inStack.pop());		// 双数进左边栈
			else 				right.push(inStack.pop());		// 单数进右边栈
		}

		left = mergesort(left);		// 已经排好序的从小到大的左边栈
		right = mergesort(right);	// 已经排好序的从小到大的右边栈
		
		// 合并：谁小先推谁，所以从栈顶到栈底是由大到小！
		while (left.size() > 0 || right.size() > 0) {
			if (left.size() == 0)				// 左空栈，直接推右边
				inStack.push(right.pop());
			else if (right.size() == 0) 			// 右空栈，直接推左边
				inStack.push(left.pop());
			else if (right.peek() > left.peek())		// 栈顶元素谁大谁先入栈，这样
				inStack.push(left.pop());
			else
				inStack.push(right.pop());
		}
		
		// 再把刚才merge的结果反转一下：栈顶到栈底由小到大！
		Stack<Integer> reverseStack = new Stack<Integer>();	// 再反转一次就是栈底到栈顶是从大到小
		while (inStack.size() > 0)
			reverseStack.push(inStack.pop());

		return reverseStack;		// 返回已经排好序的新栈！
	}
	
	/* Solution 2: 通过原数组来做缓存来帮助新增加的数组排序成从大到小 */

	public static void sort(Stack<Integer> s) {
		Stack<Integer> r = new Stack<Integer>();		// 栈顶到栈底： 从大到小！
		while (!s.isEmpty()) {
			/* Insert each element in s in sorted order into r. */
			int tmp = s.pop();
			while (!r.isEmpty() && r.peek() > tmp) {
				s.push(r.pop());
			}
			r.push(tmp);
		}

		/* Copy the elements back. */
		while (!r.isEmpty()) 
			s.push(r.pop());
	}

//	public static void main(String[] args) {
//		Stack<Integer> s = new Stack<Integer>();
//		for (int i = 0; i < 10; i++) {
//			int r = AssortedMethods.randomIntInRange(0, 1000);
//			s.push(r);
//		}
//
//		mergesort(s);
//
//		while (!s.isEmpty()) {
//			System.out.println(s.pop());
//		}
//	}
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < 10; i++) {
			int r = AssortedMethods.randomIntInRange(0, 1000);
			s.push(r);
		}

		Stack tStack = mergesort(s);

		while (!tStack.isEmpty()) {
			System.out.println(tStack.pop());
		}
	}
}
