package hashTable;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Jan 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DailyTemperatures_739 {
	
	/* Stack */
	
	public int[] dailyTemperatures(int[] temp) {
		Stack<Integer> stack = new Stack<>();
		int[] ret = new int[temp.length];
		
		for (int i = 0; i < temp.length; i++) {
			while (!stack.isEmpty() && temp[i] > temp[stack.peek()]) {
				int idx = stack.pop();
				ret[idx] = i - idx;
			}
			stack.push(i);
		}
		
		return ret;
	}
	
	/* Array */
	
	public int[] dailyTemperatures2(int[] temp) {
		int[] stack = new int[temp.length];
		int top = -1;
		int[] ret = new int[temp.length];
		
		for (int i = 0; i < temp.length; i++) {
			while (top > -1 && temp[i] > temp[stack[top]]) {
				int idx = stack[top--];
				ret[idx] = i - idx;
			}
			stack[++top] = i;
		}
		
		return ret;
	}
	
	
	public int[] dailyTemperatures3(int[] temps) {
		int n = temps.length;
		int[] waits = new int[n];
		int[] next = new int[101]; 		// next day with with certain temperature.
		
		for (int i = n - 1; i >= 0; i--) {
			int earliest = Integer.MAX_VALUE;
			for (int t = temps[i] + 1; t <= 100; t++) {
				if (next[t] != 0)
					earliest = Math.min(earliest, next[t]);
			}
			if (earliest != Integer.MAX_VALUE)
				waits[i] = earliest - i;
			next[temps[i]] = i;
		}
		
		return waits;
	}
}
