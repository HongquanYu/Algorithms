package wayfair;

/** @author: Hongquan Yu
 *  @date: Mar 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SubstringSearch {
	public static boolean contains(String string, String p) {
		if (string == null || p == null)
			return false;
		
		for (int i = 0; i < string.length(); ++i) {
			if (string.charAt(i) == p.charAt(0)) {
				int j = 1;
				for ( ; j < p.length(); ++j) {
					if (string.charAt(i + j) != p.charAt(j))
						break;
				}
				if (j == p.length() && string.charAt(i + j - 1) == p.charAt(j - 1))
					return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String s1 = "substring";
		String s2 = "ubs";
		String s3 = "n";
		
		System.out.println(contains(s1, s2));
		System.out.println(contains(s1, s3));
	}
}
