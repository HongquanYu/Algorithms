package binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class IsSubsequence_392 {
	
	/* Linear solution */
	
    public boolean isSubsequence1(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        
        int p = 0;
        for (int i = 0; i < n2; ++i) {
            if (p == n1)
                return true;
            if (s.charAt(p) == t.charAt(i))
                p++;
        }
        
        return p == n1;
	}
	
    public static  boolean isSubsequence(String s, String t) {
		List<Integer>[] charIndex = new List[26]; 		// 链表数据，即每一个元素都是个数组
		
		// 把每一个出现的字符和它们的索引都存在数组里
		for (int i = 0; i < t.length(); i++) {
			if (charIndex[t.charAt(i) - 'a'] == null)
				charIndex[t.charAt(i) - 'a'] = new ArrayList<>();
			charIndex[t.charAt(i) - 'a'].add(i);
		}
		
		int next = 0;	// 存储的是t中下一个查询的位置，即从next位置开始查
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'a';
			if (charIndex[idx] == null)	// 字符串t里根本没有这个字符
				return false;
			
			// 查询有没有出现过当前字符，要是有则能找到索引，然后在后面更新next到其下一个。要是没有，则判断其是否后面包涵，没到末尾就表示后面还有出现此字符。
			int j = Collections.binarySearch(charIndex[idx], next);

			if (j < 0)	j = -j - 1;								// not found, get the insertion point

			if (j == charIndex[idx].size())		// 数组里已经没有多余的元素可以查了， 要不是最后一个元素，隐含着后面还有元素来match当前字符
				return false;
			
			next = charIndex[idx].get(j) + 1;	// 指向match到元素位置的下一个位置
		}
		
		return true;
	}
    
    public static void main(String [] args) {
    		String s = "abcdhsgsdhjsdg", t = "sgasbsscgggdhhshgjskkkdhlljlsdsgfdlggl";
    		
		System.out.println("res: " + isSubsequence(s, t));
    }
}
