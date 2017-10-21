package hashtable;

public class MinimumWindowSubstring_76 {
	public String minWindow(String s, String t) {
		int map[] = new int[128];
		for (char c : t.toCharArray())
			map[c]++;
		int i = 0, j = 0, cnt = t.length(), min = Integer.MAX_VALUE, start = 0;
		while (j < s.length()) {
			if (map[s.charAt(j++)]-- > 0)
				cnt--;
			while (cnt == 0) {
				if (j - i < min)
					min = j - (start = i);
				if (map[s.charAt(i++)]++ == 0)
					cnt++;
			}
		}

		return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
	}
}
