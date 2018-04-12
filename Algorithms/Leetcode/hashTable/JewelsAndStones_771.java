package hashTable;

/** @author: Hongquan Yu
 *  @date: Apr 4, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class JewelsAndStones_771 {
	
	public int numJewelsInStones(String J, String S) {
		int count = 0;
		for (char c : S.toCharArray()) {
			if (J.indexOf(c) >= 0)
				count++;
		}
		return count;
	}

	public int numJewelsInStones2(String J, String S) {
		return S.replaceAll("[^" + J + "]", "").length();
	}
}
