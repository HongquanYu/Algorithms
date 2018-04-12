package ArrayString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntPredicate;

/** @author: Hongquan Yu
 *  @date: Jan 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CheckPermutation_2 {
	
	/** Ask questions before start to solve it:
	 *  1, Is it case sensitive? "God" - "dog"
	 *  2, Is whitespace significant? 
	 *  
	 *  当然这个下面hashmap的解法还可以使用数组来计数！ */
	
	
	public static boolean isPermutation(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		
		Map<Character, Integer> map = new HashMap<>();
		Map<Character, Integer> map2 = new HashMap<>();
		
		for (int i = 0; i < s1.length(); ++i)
			map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
		
		for (int j = 0; j < s1.length(); ++j)
			map2.put(s2.charAt(j), map2.getOrDefault(s2.charAt(j), 0) + 1);
		
		if (map.size() != map2.size())
			return false;
		
		for (char c : map.keySet()) {
			if (!map2.containsKey(c) || map.get(c) != map2.get(c))
				return false;
		}
		
		return true;
	}
	
	/* Solution 2:
	 * Sort the two strings and compared the sorted string if there are the same. */
	
	public static boolean isPermutation2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		
		char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
		Arrays.sort(c1);
		Arrays.sort(c2);
		
		return new String(c1).equals(new String(c2));
	}
	
	/* Solution 3: use array to imitate hashmap */
	
	public static boolean isPermutation3(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		
		int[] letters = new int[128];	// Assumption: ASCII
		
		for (int i = 0; i < s1.length(); ++i)
			letters[s1.charAt(i)]++;
		
		for (int j = 0; j < s2.length(); ++j) {
			letters[s2.charAt(j)]--;
			if (letters[s2.charAt(j)] < 0)
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String s1 = "abcdD";
		String s2 = "dcba";
		
		String s3 = "bbbb";
		String s4 = "bbbb";
		
		String s5 = "235";
		String s6 = "235";
		
		String s7 = "./^";
		String s8 = "^./";
		
		System.out.println(isPermutation(s1, s2));
		System.out.println(isPermutation(null, null));
		
		System.out.println(isPermutation(s3, s4));
		System.out.println(isPermutation(s5, s6));
		System.out.println(isPermutation(s7, s8));
		
		System.out.println(isPermutation2(s1, s2));
		System.out.println(isPermutation2(null, null));
		
		System.out.println(isPermutation2(s3, s4));
		System.out.println(isPermutation2(s5, s6));
		System.out.println(isPermutation2(s7, s8));
	}
}
