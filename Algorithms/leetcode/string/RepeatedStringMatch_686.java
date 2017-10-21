package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RepeatedStringMatch_686 {
	public int repeatedStringMatch(String A, String B) {
		int q = 1;
		StringBuilder S = new StringBuilder(A);
		
		for (; S.length() < B.length(); q++)
			S.append(A);
		
		if (S.indexOf(B) >= 0)
			return q;
		if (S.append(A).indexOf(B) >= 0)
			return q + 1;
		
		return -1;
	}
}
