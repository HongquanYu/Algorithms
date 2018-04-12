package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumProductOfWordLengths_318 {
	
	/** 将每一个单词转化成数字表现形式，然后来做 */
	
	public static int maxProduct(String[] words) {
		if (words == null || words.length == 0)
			return 0;
		
		int N = words.length;
		int[] value = new int[N];
		
		// Record every string in digital representation
		for (int i = 0; i < N; i++) {
			String word = words[i];
			value[i] = 0;
			
			// Map the character to a number which each bit represents a letter in current string
			for (int j = 0; j < word.length(); j++)
				value[i] |= 1 << (word.charAt(j) - 'a');
		}
		
		// Traverse and select the maximum product
		int maxProduct = 0;
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++) {
				if ((value[i] & value[j]) == 0)
					maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
			}
		
		return maxProduct;
	}
}
