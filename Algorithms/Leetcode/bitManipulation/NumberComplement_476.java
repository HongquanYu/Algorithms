package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NumberComplement_476 {
	public int findComplement(int num) {
		int mask = (Integer.highestOneBit(num) << 1) - 1;
		return num ^ mask;
	}
}
