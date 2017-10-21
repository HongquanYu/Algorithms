package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbreviation_288 {
	private final Map<String, Set<String>> abbrDict = new HashMap<>();

	public UniqueWordAbbreviation_288(String[] dictionary) {
		for (String s : dictionary) {
			String abbr = toAbbr(s);
			Set<String> words = abbrDict.containsKey(abbr) ? abbrDict.get(abbr) : new HashSet<>();
			words.add(s);
			abbrDict.put(abbr, words);
		}
	}

	public boolean isUnique(String word) {
		String abbr = toAbbr(word);
		Set<String> words = abbrDict.get(abbr);
		return words == null || (words.size() == 1 && words.contains(word));
	}

	private String toAbbr(String s) {
		int n = s.length();
		if (n <= 2) {
			return s;
		}
		return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
	}
}
