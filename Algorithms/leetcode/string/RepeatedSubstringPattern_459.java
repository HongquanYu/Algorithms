package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RepeatedSubstringPattern_459 {
	
	/*  1, First char of input string is first char of repeated substring
	*	2, Last char of input string is last char of repeated substring
	*	3, Let S1 = S + S (where S in input string)
	*	4, Remove 1 and last char of S1. Let this be S2
	*	5, If S exists in S2 then return true else false
	*	6, Let i be index in S2 where S starts then repeated substring 
	*		length i + 1 and repeated substring S[0: i+1] */
	
	public boolean repeatedSubstringPattern(String s) {
		String c = (s + s).substring(1, s.length() + s.length() - 1);
		return c.indexOf(s) != -1;
	}
}
