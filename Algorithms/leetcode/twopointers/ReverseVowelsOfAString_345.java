package twopointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReverseVowelsOfAString_345 {
	public String reverseVowels(String s) {
		if (s == null || s.length() == 0)
			return s;
		String vowels = "aeiouAEIOU";
		char[] chars = s.toCharArray();
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {

			while (start < end && !vowels.contains(chars[start] + "")) {
				start++;
			}

			while (start < end && !vowels.contains(chars[end] + "")) {
				end--;
			}

			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;

			start++;
			end--;
		}
		return new String(chars);
	}
}
