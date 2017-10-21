package hashtable;

import java.util.HashMap;

public class IsomorphicStrings_205 {
	public boolean isIsomorphic(String s, String t) {
		if (s == null || s.length() <= 1)
			return true;
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			char b = t.charAt(i);
			if (map.containsKey(a)) {
				if (map.get(a).equals(b))
					continue;
				else
					return false;
			} else {
				if (!map.containsValue(b))
					map.put(a, b);
				else
					return false;

			}
		}
		return true;

	}
	
		public boolean isIsomorphic1(String s, String t) {
			int[] m1 = new int[256];
			int[] m2 = new int[256];
			int n = s.length();
			for (int i = 0; i < n; ++i) {
				if (m1[s.charAt(i)] != m2[t.charAt(i)])
					return false;
				m1[s.charAt(i)] = i + 1;
				m2[t.charAt(i)] = i + 1;
			}
			return true;
		}
	
	/*
	 * The main idea is to store the last seen positions of current (i-th) characters in both
	 * strings. If previously stored positions are different then we know that the fact they're
	 * occuring in the current i-th position simultaneously is a mistake. We could use a map for
	 * storing but as we deal with chars which are basically ints and can be used as indices we can
	 * do the whole thing with an array.
	 */


	public boolean isIsomorphic2(String s1, String s2) {
		int[] m = new int[512];
		for (int i = 0; i < s1.length(); i++) {
			if (m[s1.charAt(i)] != m[s2.charAt(i) + 256])
				return false;
			m[s1.charAt(i)] = m[s2.charAt(i) + 256] = i + 1;
		}
		return true;
	}
}
