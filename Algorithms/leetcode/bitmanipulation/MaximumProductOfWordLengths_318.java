package bitmanipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumProductOfWordLengths_318 {
	public static int maxProduct(String[] words) {
		if (words == null || words.length == 0)
			return 0;
		int len = words.length;
		int[] value = new int[len];
		for (int i = 0; i < len; i++) {
			String tmp = words[i];
			value[i] = 0;
			for (int j = 0; j < tmp.length(); j++) {
				value[i] |= 1 << (tmp.charAt(j) - 'a');
			}
		}
		int maxProduct = 0;
		for (int i = 0; i < len; i++)
			for (int j = i + 1; j < len; j++) {
				if ((value[i] & value[j]) == 0
						&& (words[i].length() * words[j].length() > maxProduct))
					maxProduct = words[i].length() * words[j].length();
			}
		return maxProduct;
	}
}
