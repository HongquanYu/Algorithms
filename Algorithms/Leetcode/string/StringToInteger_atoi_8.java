package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StringToInteger_atoi_8 {

	/** Very Concise Code */
	
	public static int myAtoi(String str) {
		if (str.isEmpty()) 				// 1. Empty string
			return 0;

		int sign = 1, base = 0, ptr = 0;
		while (str.charAt(ptr) == ' ') 	// 2. Remove Leading Spaces
			ptr++;

		if (str.charAt(ptr) == '-' || str.charAt(ptr) == '+') 	// 3. Handle signs
			sign = str.charAt(ptr++) == '-' ? -1 : 1;

		while (ptr < str.length() && str.charAt(ptr) >= '0' && str.charAt(ptr) <= '9') {
			if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(ptr) - '0' > 7)) {
				return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			base = 10 * base + (str.charAt(ptr++) - '0');
		}

		return base * sign;
	}
}
