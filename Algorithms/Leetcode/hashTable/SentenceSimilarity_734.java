package hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Jan 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SentenceSimilarity_734 {
	public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length)
			return false;

		Map<String, Set<String>> pairInfo = new HashMap<>();

		for (String[] pair : pairs) {	
			if (!pairInfo.containsKey(pair[0])) 
				pairInfo.put(pair[0], new HashSet<>());
			
			if (!pairInfo.containsKey(pair[1])) 
				pairInfo.put(pair[1], new HashSet<>());

			pairInfo.get(pair[0]).add(pair[1]);
			pairInfo.get(pair[1]).add(pair[0]);
		}

		for (int i = 0; i < words1.length; i++) {
			if (words1[i].equals(words2[i]))
				continue;

			if (!pairInfo.containsKey(words1[i]))
				return false;
			
			if (!pairInfo.get(words1[i]).contains(words2[i]))
				return false;
		}

		return true;
	}
}
