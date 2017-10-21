package bitmanipulation;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumUniqueWordAbbreviation_411 {
	int n, cand, bn, minlen, minab;
	List<Integer> dict = new ArrayList<>();


	// Abbreviation for one digit is meaningless, thus at least two digits are used for
	// abbreviation.
	private int abbrLen(int mask) {
		int count = n;
		for (int b = 3; b < bn; b <<= 1)
			if ((mask & b) == 0)
				count--;
		return count;
	}

	private void dfs(int bit, int mask) {
		int len = abbrLen(mask);
		if (len >= minlen)
			return;
		boolean match = true;
		for (Integer d : dict) {
			if ((mask & d) == 0) {
				match = false;
				break;
			}
		}
		// a mask which can cover all differences, no need to find more.
		if (match) {
			minlen = len;
			minab = mask;
		}
		// No ? Then has to add more masks to cover all differences.
		else {
			for (int b = bit; b < bn; b <<= 1) {
				if ((cand & b) != 0)
					dfs(b << 1, mask + b);
			}
		}
	}

	String minAbbreviation(String target, String[] dictionary) {
		n = target.length();
		bn = 1 << n;
		cand = 0;
		minlen = Integer.MAX_VALUE;
		StringBuilder res = new StringBuilder();

		for (String s : dictionary) {
			int word = 0;
			if (s.length() != n)
				continue;
			for (int i = 0; i < n; i++)
				if (target.charAt(i) != s.charAt(i))
					word |= 1 << i;
			dict.add(word);
			cand |= word;
		}

		dfs(1, 0); // DFS : 1 -> 1010 -> 10101
					// -> 10100
					// -> 1011 -> 10110

		for (int i = 0; i < n;) {
			if ((minab & (1 << i)) != 0) {
				res.append(target.charAt(i++));
			} else {
				int j = i;
				while (i < n && (minab & (1 << i)) == 0)
					i++;
				res.append(i - j);
			}
		}

		return res.toString();
	}
	
	
	/* Trie */
	
	class Trie {
		Trie[] next = new Trie[26];
		boolean isEnd = false;
	}

	Trie root = new Trie();
	List<String> abbrs;

	public String minAbbreviation2(String target, String[] dictionary) {
		for (String s : dictionary) {
			addTrie(s);
		}
		for (int i = 0; i < target.length(); i++) {
			abbrs = new ArrayList<>();
			abbrGenerator(target, 0, "", 0, i + 1);
			for (String s : abbrs) {
				if (search(s, root, 0, 0) == false)
					return s;
			}
		}
		return "";
	}

	public void addTrie(String s) {
		Trie cur = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (cur.next[c - 'a'] == null) {
				cur.next[c - 'a'] = new Trie();
			}
			cur = cur.next[c - 'a'];
		}
		cur.isEnd = true;
	}

	public boolean search(String target, Trie root, int i, int loop) {
		if (root == null)
			return false;

		if (loop != 0) {
			for (int a = 0; a < 26; a++) {
				if (search(target, root.next[a], i, loop - 1))
					return true;
			}
			return false;
		}
		if (i == target.length()) {
			if (root.isEnd)
				return true;
			return false;
		}
		if (Character.isDigit(target.charAt(i))) {
			int tmp = 0;
			while (i < target.length() && Character.isDigit(target.charAt(i))) {
				tmp = tmp * 10 + target.charAt(i) - '0';
				i++;
			}
			return search(target, root, i, tmp);
		} else {
			return search(target, root.next[target.charAt(i) - 'a'], i + 1, 0);
		}
	}

	public void abbrGenerator(String target, int i, String tmp, int abbr, int num) {
		if (i == target.length()) {
			if (num == 0 && abbr == 0)
				abbrs.add(tmp);
			if (num == 1 && abbr != 0)
				abbrs.add(tmp + abbr);
			return;
		}
		if (num <= 0)
			return;
		char cur = target.charAt(i);
		abbrGenerator(target, i + 1, abbr == 0 ? tmp + cur : tmp + abbr + cur, 0,
				abbr == 0 ? num - 1 : num - 2);
		abbrGenerator(target, i + 1, tmp, abbr + 1, num);
	}
}
