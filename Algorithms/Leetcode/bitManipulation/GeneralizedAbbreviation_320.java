package bitManipulation;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class GeneralizedAbbreviation_320 {
	
	/* We are going to exploit the fact:
	 *  If we observe the result set carefully, we can find that the number of abbreviate is 
	 *  equal to the 2^word.length. Further more, each number with the 2^word.length has a 
	 *  one to one map to the solution set! So we can exploit this fact and use 0 to represent 
	 *  a character that is not abbreviated and 1 to represent one that is. 
	 *  Then each abbreviation is mapped to an n bit binary number and vice versa. */
	
	public List<String> generateAbbreviations(String word) {
		List<String> ans = new ArrayList<>();
		int len = 1 << word.length();
		
		/* loop through all possible x 
		 * There are (1 << word.length()) abbreviations we can get */
		for (int x = 0; x < len; ++x)
			ans.add(abbr(word, x, len));
		
		return ans;
	}

	// build the abbreviation for word from number x
	private String abbr(String word, int x, int len) {
		StringBuilder builder = new StringBuilder();
		
		// k is the length of consecutive bits which is set to '1' in x
		int k = 0;
		
		for (int i = 0; i < len; ++i, x >>= 1) {		// word length times of 
			
			// We encounter a '0', then we add the abbreviated length bits to string and keep word.charAt(i)
			if ((x & 1) == 0) { 				// check the last bit if it is '1'
				if (k != 0) { 				// So far we have abbreviated k characters, so we insert number k into string, and reset k
					builder.append(k);
					k = 0;
				}
				
				builder.append(word.charAt(i));	// Insert current character which should not be abbreviated 
			}
			else 	// bit is one, increase k
				++k;
		}
		
		if (k != 0)
			builder.append(k); 		// don't forget to append the last k if non zero
		
		return builder.toString();
	}
}
