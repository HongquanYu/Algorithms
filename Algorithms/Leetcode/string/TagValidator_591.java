package string;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TagValidator_591 {
	
	/** Solution 1 */
	
	public boolean isValid(String code) {
		Stack<String> stack = new Stack<>();
		
		for (int i = 0; i < code.length();) {
			if (i > 0 && stack.isEmpty())
				return false;
			if (code.startsWith("<![CDATA[", i)) {
				int j = i + 9;
				i = code.indexOf("]]>", j);
				if (i < 0)
					return false;
				i += 3;
			} else if (code.startsWith("</", i)) {
				int j = i + 2;
				i = code.indexOf('>', j);
				if (i < 0 || i == j || i - j > 9)
					return false;
				for (int k = j; k < i; k++) {
					if (!Character.isUpperCase(code.charAt(k)))
						return false;
				}
				String s = code.substring(j, i++);
				if (stack.isEmpty() || !stack.pop().equals(s))
					return false;
			} else if (code.startsWith("<", i)) {
				int j = i + 1;
				i = code.indexOf('>', j);
				if (i < 0 || i == j || i - j > 9)
					return false;
				for (int k = j; k < i; k++) {
					if (!Character.isUpperCase(code.charAt(k)))
						return false;
				}
				String s = code.substring(j, i++);
				stack.push(s);
			} else { i++; }
		}
		return stack.isEmpty();
	}
	
	/** Solution 2 */
	
	Stack<String> stack = new Stack<>();
	boolean contains_tag = false;

	public boolean isValidTagName(String s, boolean ending) {
		if (s.length() < 1 || s.length() > 9)
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isUpperCase(s.charAt(i)))
				return false;
		}
		if (ending) {
			if (!stack.isEmpty() && stack.peek().equals(s))
				stack.pop();
			else
				return false;
		} else {
			contains_tag = true;
			stack.push(s);
		}
		return true;
	}

	public boolean isValidCdata(String s) {
		return s.indexOf("[CDATA[") == 0;
	}

	public boolean isValid2(String code) {
		if (code.charAt(0) != '<' || code.charAt(code.length() - 1) != '>')
			return false;
		for (int i = 0; i < code.length(); i++) {
			boolean ending = false;
			int closeindex;
			if (stack.isEmpty() && contains_tag)
				return false;
			if (code.charAt(i) == '<') {
				if (!stack.isEmpty() && code.charAt(i + 1) == '!') {
					closeindex = code.indexOf("]]>", i + 1);
					if (closeindex < 0 || !isValidCdata(code.substring(i + 2, closeindex)))
						return false;
				} else {
					if (code.charAt(i + 1) == '/') {
						i++;
						ending = true;
					}
					closeindex = code.indexOf('>', i + 1);
					if (closeindex < 0
							|| !isValidTagName(code.substring(i + 1, closeindex), ending))
						return false;
				}
				i = closeindex;
			}
		}
		return stack.isEmpty() && contains_tag;
	}
}
