package bitManipulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RepeatedDNASequences_187 {
	public List<String> findRepeatedDnaSequences(String s) {
		Set<Integer> words = new HashSet<>();
		Set<Integer> seen = new HashSet<>();
		List<String> rv = new ArrayList<>();
		char[] map = new char[26];
		// map['A' - 'A'] = 0;	// 0000
		map['C' - 'A'] = 1;		// 0001
		map['G' - 'A'] = 2;		// 0010
		map['T' - 'A'] = 3;		// 0011

		for (int i = 0; i < s.length() - 9; i++) {	// Traverse the string
			int v = 0;
			for (int j = i; j < i + 10; j++) {		// For every valid range
				v <<= 2;			// every two bits record a letter in the string
				v |= map[s.charAt(j) - 'A'];
			}
			if (!words.add(v) && seen.add(v)) {
				rv.add(s.substring(i, i + 10));
			}
		}
		return rv;
	}
	
	public List<String> findRepeatedDnaSequences2(String s) {
		Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
		for (int i = 0; i + 9 < s.length(); i++) {
			String ten = s.substring(i, i + 10);
			if (!seen.add(ten))
				repeated.add(ten);
		}
		
		return new ArrayList<String>(repeated);
	}
}
