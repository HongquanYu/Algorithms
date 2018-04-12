package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StringCompression_6 {
	
	/** First solution */
	
	public static String compression(String s) {
		if (s == null)
			return s;
		StringBuilder sb = new StringBuilder();
		int N = s.length();
		
		for (int i = 0; i < N; ) {
			sb.append(s.charAt(i));
			int j = 1, count = 1;
			while (i + j < N && s.charAt(i + j) == s.charAt(i)) {
				count++;
				j++;
			}
//			if (j != 1)		// 只有一个字母的不能加数字
				sb.append(count);
			i += j;
		}
		
		return (sb.length() < N) ? sb.toString() : s;
	}
	
	/* Second Solution */
	
	public static String compression2(String s) {
		if (s == null)
			return s;
		String res = "";
		int count = 0, N = s.length();
		
		for (int i = 0; i < N; ++i) {
			count++;
			
			if (i + 1 >= N || s.charAt(i) != s.charAt(i + 1)) {	// 最后一个字符的时候 || 下一个字符和当前不同
				res += "" + s.charAt(i) + count;
				count = 0;
			}
		}
		
		return res.length() < N ? res : s;
	}
	
	/** Third Solution */
	
	public static String compression3 (String s) {
		if (s == null)
			return s;
		StringBuilder res = new StringBuilder();
		int count = 0, N = s.length();
		
		for (int i = 0; i < N; ++i) {
			count++;
			
			if (i + 1 >= N || s.charAt(i) != s.charAt(i + 1)) {
				res.append(s.charAt(i));
				if (count != 1)	res.append(count);
				count = 0;
			}
		}
		
		return res.length() < N ? res.toString() : s;
	}
	
	public static void main(String[] args) {
		String s1 = "aabcccccaaa";
		String s2 = "";
		String s3 = "abcde";
		String s4 = "11222";
		
		System.out.println("First solution: ");
		System.out.println(compression(s1));
		System.out.println(compression(s2));
		System.out.println(compression(null));
		System.out.println(compression(s3));
		System.out.println(compression(s4));
		System.out.println("------------------------");
		System.out.println("Second solution: ");
		System.out.println(compression2(s1));
		System.out.println(compression2(s2));
		System.out.println(compression2(null));
		System.out.println(compression2(s3));
		System.out.println(compression2(s4));
		System.out.println("------------------------");
		System.out.println("Third solution: ");
		System.out.println(compression3(s1));
		System.out.println(compression3(s2));
		System.out.println(compression3(null));
		System.out.println(compression3(s3));
		System.out.println(compression3(s4));
	}
}
