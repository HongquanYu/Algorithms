package bitmanipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BinaryNumberWithAlternatingBits_693 {
	public boolean hasAlternatingBits(int n) {
		String bits = Integer.toBinaryString(n);
		for (int i = 0; i < bits.length() - 1; i++) {
			if (bits.charAt(i) == bits.charAt(i + 1)) {
				return false;
			}
		}
		
		return true;
	}
}
