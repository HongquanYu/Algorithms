package stack;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class NextGreaterElementII_503 {
	
	/** 使用一个栈来存 索引， */
	
	public int[] nextGreaterElements(int[] nums) {
		int[] res = new int[nums.length];
		int N = nums.length;
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 2 * N - 1; i >= 0; --i) {
			while (!stack.empty() && nums[stack.peek()] <= nums[i % N])
				stack.pop();
			
			res[i % N] = stack.empty() ? -1 : nums[stack.peek()];
			stack.push(i % N);
		}
		return res;
	}
}
