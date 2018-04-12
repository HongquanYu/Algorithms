package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PalindromePermutation_4 {
	
	public static boolean isPermutationOfPalindrome(String phrase) {
		int [] table = buildCharFrequencyTable(phrase);
		return checkMaxOneAdd(table);
	}
	
	private static boolean checkMaxOneAdd(int[] table) {
		boolean foundOdd = false;
		
		for (int c : table) {
			if (c % 2 == 1) {
				if (foundOdd) return false;
				foundOdd = true;
			}
		}
		
		return true;
	}
	
	private static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		
		return (a <= val && val <= z) ? (val - a) : -1;
	}
	
	private static int[] buildCharFrequencyTable(String phrase) {
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		
		for(char c : phrase.toCharArray()) {
			int x = getCharNumber(c);
			if (x == -1)		table[x]++;
		}
		return table;
	}
	
	public static boolean isPalPermutation(String s) {
		if (s == null || s.length() == 0)
			return false;
		
		s = s.toLowerCase();
		int[] str = new int[128];
		boolean foundOdd = false;
		
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) != ' ')
				str[s.charAt(i)]++;
		}
		
		for (int p : str) {		// 不用再scan原数组了！！！
			if (p % 2 != 0) {
				if (foundOdd)	return false;
				foundOdd = true;
			}
		}
		
		return true;
	}
	
	/* Improvements from above solution */
	public static boolean isPalPermutation2(String s) {
		if (s == null || s.length() == 0)
			return false;
		
		s = s.toLowerCase();
		int[] str = new int[128];
		int countOdd = 0;
		
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) != ' ') {
				str[s.charAt(i)]++;
				if (str[s.charAt(i)] % 2 != 0) 		countOdd++;
				else									countOdd--;
			}
		}
		return countOdd <= 1;
	}
	
	public static void main(String[] args) {
		String s1 = "Tact Coa";
		String s2 = "aaa";
		String s3 = "1221363";
		String s4 = "\0\0\00";
		String s5 = "jfdloafhh";
		
		System.out.println("null:	" + isPalPermutation(null));
		System.out.println("Tact Coa:	" + isPalPermutation(s1));
		System.out.println("aaa:		" + isPalPermutation(s2));
		System.out.println("aaa:		" + isPermutationOfPalindrome(s2));
		System.out.println("aaa -:		" + isPalPermutation2(s2));
		System.out.println("jfdloafhh 2:		" + isPalPermutation2(s5));
		System.out.println("jfdloafhh 1:		" + isPalPermutation(s5));
		System.out.println("1221363:		" + isPalPermutation(s3));
		System.out.println("\\0\\0\\00:		" + isPalPermutation(s4));
	}
}
