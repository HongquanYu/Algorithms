package hashTable;

import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Jan 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindTheDifference_389 {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < t.length(); ++i)
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
        
        for (char c : map.keySet()) {
            if (map.get(c) == 1)
                return c;
        }
        
        return ' ';
    }
    
    /* Bit Manipulation. */
    
	public char findTheDifference1(String s, String t) {
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		
		char result = 0;
		int len = a.length;
		
		for (int i = 0; i < len; i++)
			result = (char) (result ^ a[i] ^ b[i]);
		
		return (char) (result ^ b[len]);
	}
}
