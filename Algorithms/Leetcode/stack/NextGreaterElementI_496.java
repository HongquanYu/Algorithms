package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class NextGreaterElementI_496 {
	
	/** 用 HashMap 存元素和他后面第一个比他大的元素。然后遍历并找到每一个元素的后续大元素 */

	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
		Stack<Integer> stack = new Stack<>();
		
		for (int num : nums) {
			while (!stack.isEmpty() && stack.peek() < num)	// 弹出之前所有比小的元素
				map.put(stack.pop(), num);
			stack.push(num);
		}
		for (int i = 0; i < findNums.length; i++)
			findNums[i] = map.getOrDefault(findNums[i], -1);
		
		return findNums;
	}
}
