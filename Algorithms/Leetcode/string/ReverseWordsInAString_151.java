package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReverseWordsInAString_151 {
	
	/** Most Concise Solution */
	
	public String reverseWords(String s) {
		String[] parts = s.trim().split("\\s+");	// \s+ 匹配任何 whitespace
		String out = "";
		for (int i = parts.length - 1; i > 0; i--)
			out += parts[i] + " ";
		
		return out + parts[0];	// 最后一个后面不能用空格
	}
	
	/** Solution with all self-defined operations！
	 *  This solution is astonishing and fantastic! */
	
	public String reverseWords2(String s) {
		if (s == null) 	return null;

		char[] a = s.toCharArray();
		int N = a.length;

		reverse(a, 0, N - 1); 		// step 1. reverse the whole string
		reverseWords(a, N); 			// step 2. reverse each word
		return cleanSpaces(a, N); 	// step 3. clean up spaces
	}
	
	// 将一个连续字符的单词给 reverse 了
	void reverseWords(char[] a, int N) {
		int start = 0, end = 0;

		while (start < N) {		// 遍历整个数组
			while (start < end || start < N && a[start] == ' ')	start++; 	// skip spaces，找到一个字母， start < end就可以迈进是因为要掠过当前处理过的单词
			while (end < start || end < N && a[end] != ' ')		end++; 		// skip non spaces，找到一个空白
			reverse(a, start, end - 1); 										// reverse the word
		}
	}

	// 减去字符前面，后面和中间的 whitespace。 trim leading, trailing and multiple spaces
	String cleanSpaces(char[] a, int N) {
		int res = 0, ptr = 0;

		while (ptr < N) {	// 遍历整个字符数组
			while (ptr < N && a[ptr] == ' ')		ptr++; 					// 忽略掉空白字符
			while (ptr < N && a[ptr] != ' ')		a[res++] = a[ptr++]; 	// 拷贝 非空白字符
			while (ptr < N && a[ptr] == ' ')		ptr++; 					// 忽略掉空白字符， 确保最后一个单词后面的空格也被排除掉
			if (ptr < N)		// 不是结尾字符串，就给附上一个 whitespace
				a[res++] = ' '; 
		}

		return new String(a).substring(0, res);
	}

	// reverse a[] from a[i] to a[j]
	private void reverse(char[] a, int i, int j) {
		while (i < j) { 
			char t = a[i];
			a[i++] = a[j];
			a[j--] = t;
		}
	}
}
