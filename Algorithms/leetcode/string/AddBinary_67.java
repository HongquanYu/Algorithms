package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class AddBinary_67 {
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int lenA = a.length() - 1, lenB = b.length() - 1, carry = 0;

		while (lenA >= 0 || lenB >= 0) { // traverse all bits in both strings
			int sum = carry;
			if (lenB >= 0)
				sum += b.charAt(lenB--) - '0'; // auto cast to int
			if (lenA >= 0)
				sum += a.charAt(lenA--) - '0';
			sb.append(sum % 2);
			carry = sum / 2;
		}
		if (carry != 0)
			sb.append(carry);
		return sb.reverse().toString(); // reverse LSB and MSB
	}
}
