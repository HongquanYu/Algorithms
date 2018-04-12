package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class BitwiseANDOfNumbersRange_201 {
	
	/* The idea is very simple:
	 *  To find the prefix of all the numbers in the range, to do that, we need to emit bit of right one by one.
	 *  moveFactor has recorded how many bits we have moved. 
	 * 
	 * 1, last bit of (odd number & even number) is 0.
	 * 2, when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
	 * 3, Move m and n right a position.
	 * 
	 * Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time. */
	
	public int rangeBitwiseAnd(int m, int n) {
		if (m == 0) {		// Everything bitwise and with 0 is 0
			return 0;
		}
		int moveFactor = 1;		// how many bits have moved. 1, 2, 4, 8, 16 = 0, 1, 2, 3, 4 bits have moved
		while (m != n) {
			m >>= 1;
			n >>= 1;
			moveFactor <<= 1;
		}
		/* Right now m is the prefix of numbers
		 We have moved moveFactor bits to right, if we want to get the common prefix, we move left moveFactor bits.*/
		
		return m * moveFactor;	// prefix * moveFactor: left shift moveFactor bits
	}
	
	public int rangeBitwiseAnd1(int m, int n) {
		int i = 0; // i means we have how many bits are 0 on the right
		while (m != n) {
			m >>= 1;
			n >>= 1;
			i++;
		}
		return m << i;
	}
}
