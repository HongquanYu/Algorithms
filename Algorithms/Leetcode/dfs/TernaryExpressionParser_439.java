package dfs;

import java.util.Deque;
import java.util.LinkedList;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TernaryExpressionParser_439 {
	
	public String parseTernary(String expression) {
		if (expression == null || expression.length() == 0) {
			return expression;
		}
		char[] exp = expression.toCharArray();

		return DFS(exp, 0, exp.length - 1) + "";

	}

	public char DFS(char[] c, int start, int end) {
		if (start == end) {
			return c[start];
		}
		int count = 0, i = start;
		for (; i <= end; i++) {
			if (c[i] == '?') {
				count++;
			} else if (c[i] == ':') {
				count--;
				if (count == 0) {
					break;
				}
			}
		}
		return c[start] == 'T' ? DFS(c, start + 2, i - 1) : DFS(c, i + 1, end);
	}
	
	public String parseTernary1(String expression) {
		if (expression == null || expression.length() == 0)
			return "";
		Deque<Character> stack = new LinkedList<>();

		for (int i = expression.length() - 1; i >= 0; i--) {
			char c = expression.charAt(i);
			if (!stack.isEmpty() && stack.peek() == '?') {

				stack.pop(); // pop '?'
				char first = stack.pop();
				stack.pop(); // pop ':'
				char second = stack.pop();

				if (c == 'T')
					stack.push(first);
				else
					stack.push(second);
			} else {
				stack.push(c);
			}
		}

		return String.valueOf(stack.peek());
	}
}
