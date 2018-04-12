package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class OneAway_5 {
	public static boolean oneEditAway1(String first, String second) {
		if (first == null || second == null)
			return false;
		
		int n1 = first.length(), n2 = second.length();
		
		if (n1 == n2)
			return oneEditReplace(first, second);
		else if (n1 + 1 == n2)
			return oneEditInsert(first, second);
		else if (n1 - 1 == n2)
			return oneEditInsert(second, first);
		
		return false;
	}
	
	// 只需要遍历两个string看他们是不是只有一个字母不一样
	private static boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		
		for (int i = 0; i < s1.length(); ++i) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (foundDifference)		return false;
				foundDifference = true;
			}
		}
		return (foundDifference) ? true : false;
	}
	
	// 看s1是不是只需要添加一个字符就能成为s2
	private static boolean oneEditInsert(String s1, String s2) {
		int idx1 = 0, idx2 = 0;
		
		while (idx2 < s2.length() && idx1 < s1.length()) {
			if (s1.charAt(idx1) != s2.charAt(idx2)) {
				if (idx1 != idx2)	return false;	// 已经存在过一个不一样的了
				idx2++;		// 
			} else {
				idx1++;
				idx2++;
			}
		}
		return true;
	}
	
	/* Improvements */
	
	public static boolean oneEditAway2(String first, String second) {
		if (first == null || second == null)
			return false;
		
		int n1 = first.length(), n2 = second.length();
		
		if (Math.abs(n1 - n2) > 1) return false;					// 长度不满足要求
		if (n1 == n2 && first.equals(second)) return false;		// 相同的字符串不能算
		if (n1 > n2)	 return oneEditAway2(second, first);			// 保证第一个字符串比较短
		
		int idx1 = 0, idx2 = 0;
		boolean foundDifference = false;
		while (idx2 < n2 && idx1 < n1) {
			if (first.charAt(idx1) != second.charAt(idx2)) {
				if (foundDifference)		return false;
				foundDifference = true;
				
				if (n1 == n2)	idx1++;		// on replace, move shorter pointer
			} else idx1++;
			idx2++;		// 不管怎样都得move 长的那个pointer
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String s1 = "abcd";
		String s2 = "abcde";
		
		String s3 = "abcd";
		String s4 = "abcd";
		
		String s5 = "abcd";
		String s6 = "abce";
		
		String s7 = "d";
		String s8 = "e";

		System.out.println(" - " + oneEditAway1(null, null));
		System.out.println(" - " + oneEditAway1("", ""));
		System.out.println(" - " + oneEditAway1(s1, s2));
		System.out.println(" - " + oneEditAway1(s3, s4));
		System.out.println(" - " + oneEditAway1(s5, s6));
		System.out.println(" - " + oneEditAway1(s7, s8));
		System.out.println(" - - - - -- - -- -- - -- -- -- --  -- - ");
		System.out.println(" - " + oneEditAway2(null, null));
		System.out.println(" - " + oneEditAway2("", ""));
		System.out.println(" - " + oneEditAway2(s1, s2));
		System.out.println(" - " + oneEditAway2(s3, s4));
		System.out.println(" - " + oneEditAway2(s5, s6));
		System.out.println(" - " + oneEditAway2(s7, s8));
	}
}
