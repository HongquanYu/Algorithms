package ArrayString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Jan 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class isUnique_1 {
	
	// HashSet
	public static boolean isUnique(String s) {
		if (s == null)	return false;
		
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < s.length(); ++i) {
			if (!set.add(s.charAt(i)))
				return false;
		}
		
		return true;
	}
	
	// HashMap
	public static boolean isUnique1(String s) {
		if (s == null)	return false;
		
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); ++i) {
			if (map.containsKey(s.charAt(i)))
				return false;
			map.put(s.charAt(i), 1);
		}
		
		return map.size() == s.length();
	}
	
	// Array
	public static boolean isUnique2(String s) {
		if (s == null)	return false;
		
		int[] c = new int[26];
		
		for (int i = 0; i < s.length(); ++i) {
			if (c[s.charAt(i) - 'a'] > 0)
				return false;
			c[s.charAt(i) - 'a']++;
		}
		
		return true;
	}
	
	// 排序然后做linear check！
	public static boolean isUnique3(String s) {
		if (s == null)	return false;
		
		char[] t = s.toCharArray();
		quicksort(t);
		
		for (int i = 1; i < s.length(); ++i)
			if (t[i - 1] == t[i])
				return false;
		
		return true;
	}
	
	private static void quicksort(char[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	
	private static void sort(char[] arr, int lo, int hi) {
		if (lo >= hi)	return;
		
		int p = partition(arr, lo, hi);
		sort(arr, lo, p - 1);
		sort(arr, p + 1, hi);
	}
	
	private static int partition(char[] arr, int lo, int hi) {
		int l = lo, r = hi + 1; 
		
		int p = l;
		while (true) {
			while (l != hi && arr[++l] < arr[p]) ;
			while (r != lo && arr[--r] > arr[p]) ;
			
			if (l >= r)	break;
			exch(arr, l, r);
		}
		exch(arr, r, p);
		
		return r;
	}
	
	private static void exch(char[] arr, int i, int j) {
		char t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	/** 其它的improvement idea：
	 * 1，若是字符是ascii或者extended ascii码，那么用128或者256的数组，constant space!
	 * 2，若是只是局限于小写字母26个的话，还可以用bit manipulation，1个int就有32位可以表示！
	 * 3，不能使用而外空间，可以用双重for loop，时间复杂度就上二次了！ */
	
	public static void main(String[] args) {
		String string = "abcdefghijk";
		String string1 = "abcdefghijkk";
		String s1 = "";
		
		System.out.println(isUnique(string));
		System.out.println(isUnique(string1));
		System.out.println(isUnique(s1));
		System.out.println(isUnique(null));
		
		System.out.println(isUnique1(string));
		System.out.println(isUnique1(string1));
		System.out.println(isUnique1(s1));
		System.out.println(isUnique1(null));
		
		System.out.println(isUnique3(string));
		System.out.println(isUnique3(string1));
		System.out.println(isUnique3(s1));
		System.out.println(isUnique3(null));
		
/*		char[] cs = new char[] {'s', 'd', 'h', 'y', 'i', 'r', 'p'};
		quicksort(cs);
		System.out.println(cs);*/
	}
}
