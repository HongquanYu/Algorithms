package divideandconquer;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DifferentWaysToAddParentheses_241 {
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> ret = new LinkedList<Integer>(); // result collection
		for (int i = 0; i < input.length(); i++) { // iterate every item of the input

			// For every operator, calculate it with left part with right
			if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+') {
				// Recursion to left sub-part and right sub-part
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));

				// do the calculation by operating left and right parts
				for (Integer l : left) {
					for (Integer r : right) {
						int c = 0;
						switch (input.charAt(i)) {
							case '+': {
								c = l + r;
								break;
							}
							case '-': {
								c = l - r;
								break;
							}
							case '*': {
								c = l * r;
								break;
							}
						}
						ret.add(c);
					}
				}
			}
		}
		if (ret.size() == 0)
			ret.add(Integer.parseInt(input)); // Input is a number
		return ret;
	}
}
