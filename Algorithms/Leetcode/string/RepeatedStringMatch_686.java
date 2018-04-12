package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RepeatedStringMatch_686 {
	
	/** Using wrapper methods to solve it */
	
	public int repeatedStringMatch(String A, String B) {
		int count = 1;
		StringBuilder S = new StringBuilder(A);
		
		for (; S.length() < B.length(); count++)
			S.append(A);
		
		if (S.indexOf(B) >= 0)
			return count;
		if (S.append(A).indexOf(B) >= 0)
			return count + 1;
		
		return -1;
	}
	
	/** Solve it by iteration */
	
	public int repeatedStringMatch2(String A, String B) {
		int N = A.length(), M = B.length();
		
		for (int i = 0; i < N; ++i) {
			int j = 0;
			while (j < M && A.charAt((i + j) % N) == B.charAt(j))
				j++;
			if (j == M)
				return (i + j) / N + ((i + j) % N == 0 ? 0 : 1);
		}
		return -1;
	}
}
