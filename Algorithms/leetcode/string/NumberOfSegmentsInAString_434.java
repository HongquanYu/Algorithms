package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NumberOfSegmentsInAString_434 {
	public int countSegments(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' '))
				res++;
		return res;
	}
	
	public int countSegments2(String s) {
		return ("x " + s).split(" +").length - 1;
	}
}
