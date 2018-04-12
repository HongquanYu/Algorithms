package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RepeatedSubstringPattern_459 {
	
	/******************************* 解法一 *************************************** 
	* 另外的解释：
	* 1，我们怎么判断一个字符串是否是另一个的 rotation，例如 S = "helloworld", T = "lloworldhe".
	* 我们搜索 S 是否是在 T + T 里面出现。
	* 2，对于本问题，我们只要知道 s 的某一个 rotation 等于 s 就是满足题意的。所以我们用 s + s 来找
	* 是否存在一个 s，存在就OK。 */
	
	public boolean repeatedSubstringPattern(String s) {
		String c = (s + s).substring(1, s.length() + s.length() - 1);
		return c.indexOf(s) != -1;
	}
	
	/***************************** KMP 解法 ***********************************
	 * 1，预处理 pattern 字符串，找到所有的子串的 proper prefix 并且同时是 suffix 的长度
	 * 2，为什么要比较 N % (N - len)？
	 * 		- len 是最长的 proper prefix 也是 suffix
	 * 		- N - len prefix sequence(should be length of repeated sequence)
	 * 		- 如果原数组可以 mod prefix 长度，那么则可以分。 */
	
	public boolean repeatedSubstringPattern2(String str) {
		int[] prefix = lps(str);
		int N = str.length();
		int len = prefix[N - 1];
		
		return (len > 0 && N % (N - len) == 0);
	}
	
	private int[] lps(String s) {
		int[] res = new int[s.length()];
		char[] chars = s.toCharArray();
		int i = 0, j = 1;
		while (i < chars.length && j < chars.length) {
			if (chars[j] == chars[i]) {
				res[j++] = i++ + 1;
			} else {
				if (i == 0) 	res[j++] = 0;
				else 		i = res[i - 1];
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String s1 = "abab";
		String s2 = "aba";
		String s3 = "abcabcabcabc";
		
		RepeatedSubstringPattern_459 r = new RepeatedSubstringPattern_459();
		
		System.out.println(r.repeatedSubstringPattern2(s1));
		System.out.println(r.repeatedSubstringPattern2(s2));
		System.out.println(r.repeatedSubstringPattern2(s3));
	}
}
