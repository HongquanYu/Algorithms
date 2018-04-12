package hashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbreviation_288 {
	
	private final Map<String, Set<String>> abbrDict = new HashMap<>();	// 存的是abbreviation和单词的mapping

	public UniqueWordAbbreviation_288(String[] dictionary) {
		
		for (String s : dictionary) {
			
			String abbr = toAbbr(s);
			
			if (!abbrDict.containsKey(abbr))
				abbrDict.put(abbr, new HashSet<>());
			
			abbrDict.get(abbr).add(s);
		}
	}

	public boolean isUnique(String word) {
		
		String abbr = toAbbr(word);
		
		Set<String> words = abbrDict.get(abbr);
		
		return words == null || (words.size() == 1 && words.contains(word));
	}

	private String toAbbr(String s) {
		int n = s.length();
		
		if (n <= 2) 		return s;
		
		return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
	}
}
