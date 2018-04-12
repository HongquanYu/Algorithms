package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LengthOfLastWord_58 {
	public int lengthOfLastWord(String s) {
	    return s.trim().length()-s.trim().lastIndexOf(" ")-1;
	}
	
	public int lengthOfLastWord2(String s) {
		int len = 0, tail = s.length() - 1;
		while (tail >= 0 && s.charAt(tail) == ' ')
			tail--;
		while (tail >= 0 && s.charAt(tail) != ' ') {
			len++;
			tail--;
		}

		return len;
	}
}
