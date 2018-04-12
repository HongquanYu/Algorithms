package hashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Jan 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LongestWordInDictionary_720 {
	
	/* Sort the words alphabetically, therefore shorter words always comes before longer words;
	 * Along the sorted list, populate the words that can be built;
	 * Any prefix of a word must comes before that word. */
	
    public String longestWord(String[] words) {
    	
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        
        return res;
    }
}
