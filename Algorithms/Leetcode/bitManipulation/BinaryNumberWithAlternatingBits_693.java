package bitManipulation;

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
	
	/** 正则表达式 */
	
	public boolean hasAlternatingBits2(int n) {
		return Integer.toBinaryString(n).matches("(10)*1?");
	}
	
	/** 因为是隔位为1，那么我们就 xor self 和 右移两位的结果，检查是不是只有一位（leading bit）是 1 */
	
	public boolean hasAlternatingBits3(int n) {
		return ((n ^= n/4) & n-1) == 0;
	}
}
