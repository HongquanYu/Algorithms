package bitmanipulation;

public class FindtheDifference_389 {
	public char findTheDifference(String s, String t) {
		int n = t.length();
		char c = t.charAt(n - 1);
		for (int i = 0; i < n - 1; ++i) {
			c ^= s.charAt(i);
			c ^= t.charAt(i);
		}
		return c;
	}

	public char findTheDifference1(String s, String t) {
		int charCode = t.charAt(s.length());
		// Iterate through both strings and char codes
		for (int i = 0; i < s.length(); ++i) {
			charCode -= (int) s.charAt(i);
			charCode += (int) t.charAt(i);
		}
		return (char) charCode;
	}
}
