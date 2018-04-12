package uncategorized;

/** @author: Hongquan Yu
 *  @date: Apr 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class NumberOfLinesToWriteString_806 {
	
	/**  */
	
	public int[] numberOfLines(int[] widths, String S) {
		int lines = 1, width = 0;
		for (char c : S.toCharArray()) {
			int w = widths[c - 'a'];
			width += w;
			if (width > 100) {
				lines++;
				width = w;
			}
		}

		return new int[] { lines, width };
	}
}
