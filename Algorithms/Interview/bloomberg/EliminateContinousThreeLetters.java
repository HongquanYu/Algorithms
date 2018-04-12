package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class EliminateContinousThreeLetters {
	
	/** Attempt ONE: 
	 * 只解决了出现的次数问题，但是消除一个连续的字符后，后面的字符会join到前面的来。
	 * 如果是join了过后中间有不为3的字符，也就是必须要保留的字符，这个算法也会错误的移除掉 */
	
	public String eliminate(String s) {
		if (s == null || s.length() == 0)
			return "";
		int[] letter = new int[256];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < s.length(); ++i)
			letter[s.charAt(i)]++;
		
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i);
			int count = letter[idx];
			if (count < 3 && count > 0) {
				while (count-- > 0)
					sb.append(s.charAt(i));
				letter[idx] = -1;
			}
		}
		
		return sb.toString();
	}
	
	/** Attempt TWO: 
	 *  */
	
/*	public String removeDuplicateTripleLetter(String s) {
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
		}
	}*/
	
	public String recursion(String s) {
		if (s == null || s.length() < 3) 	return s;
		if (isSingleLetter(s))			return "";
		
		int start = 0, N = s.length();
		
		for (int end = 0; end < N; ++end) {
			if (s.charAt(end) != s.charAt(start)) {
				if (end - start >= 3)
					return recursion(s.substring(0, start) + s.substring(end));
				else
					start = end;
			}
		}

		return s;
	}
	
	private boolean isSingleLetter(String s) {
		if (s.length() < 3)
			return false;
		for (int i = 1; i < s.length(); ++i)
			if (s.charAt(i) != s.charAt(0))
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		EliminateContinousThreeLetters e = new EliminateContinousThreeLetters();
		
		System.out.println("aabbbcc: " + e.recursion("aabbbcc"));
		System.out.println("aabbba: " + e.recursion("aabbba"));
		System.out.println("aaa: " + e.recursion("aaa"));
		System.out.println(": " + e.recursion(""));
		System.out.println("null: " + e.recursion(null));
		System.out.println("hhbbcckkkucbh: " + e.recursion("hhbbcckkkucbh"));
		System.out.println("albcdkabhhcdabcd: " + e.recursion("albcdkabhhcdabcd"));
		
		
		System.out.println("aabbbcc: " + e.recursion("aabbbcc"));
		System.out.println("albcdkabhhcdabcd: " + e.recursion("albcdkabhhcdabcd"));
		System.out.println("aabbcccbddee: " + e.recursion("aabbcccbddee"));
		System.out.println("aabbccckbddee: " + e.recursion("aabbccckbddee"));
		System.out.println("aaabbbccc: " + e.recursion("aaabbbccc"));
		String string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssskkkhhhnnnlllllllllllllllllllllllllllllllllllllllllllbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		System.out.println("Long: " + e.recursion(string));
	}
}
