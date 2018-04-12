package hashTable;

import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Jan 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FirstUniqueCharacterInAString_387 {
	public int firstUniqChar(String s) {
		
		int freq[] = new int[26];
		
		for (int i = 0; i < s.length(); i++)
			freq[s.charAt(i) - 'a']++;
		
		for (int i = 0; i < s.length(); i++)
			if (freq[s.charAt(i) - 'a'] == 1)
				return i;
		
		return -1;
	}
	
    public int firstUniqChar1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++)
        		map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        
        for (int i = 0; i < s.length(); i++)
        		if(map.get(s.charAt(i)) == 1)
        			return i;
        
        return -1;
    }
}
