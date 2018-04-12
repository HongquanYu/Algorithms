package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SingleNumberIII_260 {
	
	/* The general idea is:
	 *  First to get XOR of two distinguish numbers. Since they are different, their XOR must not be 0!
	 *  Thus, we can get
	 * */
	
	public int[] singleNumber(int[] nums) {
		// PASS 1: Get the XOR of the two numbers we need to find
		int diff = 0;
		for (int num : nums)
			diff ^= num;			// XOR of two appeared once numbers

		diff &= -diff;			// Last set bit to distinguish two set of numbers. diff &= ~(diff - 1)

		// PASS 2 : to divide all the number to the different categories
		int[] rets = {0, 0};
		
		for (int num : nums) {
			if ((num & diff) == 0) 		// the bit is not set
				rets[0] ^= num;
			else 						// the bit is set
				rets[1] ^= num;
		}

		return rets;
	}
}
