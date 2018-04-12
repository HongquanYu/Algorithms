package string;


/** @author: Hongquan Yu
 *  @date: Feb 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StringCompression_443 {
	
	public static int compress(char[] chars) {
		int res = 0, ptr = 0;		// 压缩完后的长度，和遍历索引
		int n = chars.length;
		
		while (ptr < n) {
			char currentChar = chars[ptr];	// 当前遍历的字符
			int count = 0;		// 记录重复字符个数
			while (ptr < n && chars[ptr] == currentChar) {
				ptr++;
				count++;
			}
			
			chars[res++] = currentChar;		// 复制当前字符到压缩后的
			
			if (count != 1)
				for (char c : Integer.toString(count).toCharArray())
					chars[res++] = c;
		}
		
		return res;
	}
	
	public static int compress2(char[] chars) {
		int res = 0, n = chars.length;
		
		for (int ptr = 0, count = 0; ptr < n; ptr++) {
			count++;
			if (ptr == n - 1 || chars[ptr] != chars[ptr + 1]) {
				chars[res] = chars[ptr];
				res++;
				if (count > 1)
					for (char c : String.valueOf(count).toCharArray())
						chars[res++] = c;

				count = 0;
			}
		}
		
		return res;
	}
    
    private static void print(char[] a, int l) {
    		for (int i = 0; i < l; ++i)
    			System.out.print(a[i] + ", ");
    		System.out.println();
    }

	public static void main(String[] args) {
		char[] cs = new char[] {'a','a','b','b','c','c','c'};
		char[] c = new char[] {'a','b','c','d','e','f','g','g','g','g','g','g','g','g','g','g','g','g','a','b','c'};
		char[] b = new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
		
		print(cs, compress2(cs));
		print(c, compress2(c));
		print(b, compress2(b));
	}
}
