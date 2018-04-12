package string;

import java.util.Stack;

public class ReverseWordsInString_151 {
	public static String reverseWords(String s) {
		Stack<StringBuilder> stk = new Stack<StringBuilder>();
		int len = s.length();
		StringBuilder word = new StringBuilder();
		StringBuilder rtn = new StringBuilder();
		for (int i = 0; i < len; ++i) {
			if (s.charAt(i) != ' ') {
				System.out.println(s.charAt(i));
				word.append(s.charAt(i));
				System.out.println("word: " + word);
			} else {
				if (word.length() != 0) {
					System.out.println("Inside else");
					stk.push(word);
					System.out.println("stk: " + stk);
					word = new StringBuilder();
					System.out.println("stk: " + stk);
				}
			}
		}
		if (word.length() != 0)
			stk.push(word);
		System.out.println("stk: " + stk);
		while (!stk.isEmpty()) {
			System.out.println("Inside construction loop");
			rtn.append(stk.pop());
			System.out.println("Current string: " + rtn);
			rtn = (!stk.isEmpty()) ? rtn.append(" ") : rtn;
		}
		// rtn.deleteCharAt(rtn.length());
		return rtn.toString();
	}
	
	public static void main(String [] args) {
		String s = "the sky is blue";
		System.out.println("Output: " + reverseWords(s));
	}
}
