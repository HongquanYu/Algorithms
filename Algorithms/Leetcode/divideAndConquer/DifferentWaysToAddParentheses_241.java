package divideAndConquer;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DifferentWaysToAddParentheses_241 {
	
	/** 通过递归来计算每一个运算符号两边的表达式的值，再用两边 list 的结果进行
	 * 当前符号的运算，加入结果 */
	
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new LinkedList<Integer>();
		
		for (int i = 0; i < input.length(); i++) {
			char ops = input.charAt(i);
			
			if (ops == '-' || ops == '*' || ops == '+') {
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));

				for (Integer l : left) {
					for (Integer r : right) {
						switch (ops) {
							case '+': { res.add(l + r); break; }
							case '-': { res.add(l - r); break; }
							case '*': { res.add(l * r); break; }
						}
					}
				}
			}
		}
		if (res.size() == 0) 	res.add(Integer.parseInt(input)); // Input is a number
		
		return res;
	}
}
