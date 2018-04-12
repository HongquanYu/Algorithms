package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CountBinarySubstrings_696 {
	public int countBinarySubstrings(String s) {
		int ans = 0, prev = 0, cur = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i - 1) != s.charAt(i)) {
				ans += Math.min(prev, cur);
				prev = cur;
				cur = 1;
			} else {
				cur++;
			}
		}
		return ans + Math.min(prev, cur);
	}
}
