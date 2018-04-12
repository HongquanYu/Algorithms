package bloomberg;

import java.awt.SecondaryLoop;

/** @author: Hongquan Yu
 *  @date: Feb 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FlipAfterTwoContinuousOne {
	public String flip(String s) {
		if (s == null || s.length() < 3)
			return s;
		
		char first = s.charAt(0);
		char second = s.charAt(1);
		StringBuilder sb = new StringBuilder(s);
		
		for (int i = 2; i < s.length(); ++i) {
			if (first == '1' && second == '1') {
				sb.setCharAt(i, sb.charAt(i) == '1' ? '0' : '1');
			}
			second = first;
			first = sb.charAt(i);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		FlipAfterTwoContinuousOne f = new FlipAfterTwoContinuousOne();
		
		System.out.println(f.flip("11011"));		// expected: 11101
		System.out.println(f.flip("111"));		// expected: 110
		System.out.println(f.flip("110"));		// expected: 111
		System.out.println(f.flip("110110"));		// expected: 111010
	}
}
