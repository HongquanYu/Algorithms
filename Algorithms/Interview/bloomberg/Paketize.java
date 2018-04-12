package bloomberg;

import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Paketize {
	private Stack<Character> stack = new Stack<>();
	private boolean getFirst = false;
	String remain = null;
	
	public void paketize(String s) {
		for (int i = 0; i < s.length(); ++i) {
			char t = s.charAt(i);
			if (t == '%')
				getFirst = true;
			stack.push(t);
			if (t == '$' && getFirst) {
				StringBuilder sb;
				
				if (remain != null)
					sb = new StringBuilder(remain);
				else 
					sb = new StringBuilder();
				
				char tmp = stack.peek();
				while(tmp != '%') {
					tmp = stack.pop();
					if (tmp != '$' && tmp != '%')
						sb.append(tmp);
				}
				
				System.out.println(sb.reverse().toString());
				getFirst = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Paketize p = new Paketize();
		
//		p.paketize("abc%def$ghi%jkl$");
		
		p.paketize("%abc");
		p.paketize("jkl");
		p.paketize("mnq$");
	}
}
