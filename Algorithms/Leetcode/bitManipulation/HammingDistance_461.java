package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class HammingDistance_461 {
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}
}
