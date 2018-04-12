package substring;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Apr 4, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class KMPStringMatching {
	
	/** 1，预处理 pattern 字符串，找到所有的子串的 proper prefix 并且同时是 suffix 的长度
	 *  2，搜索：
	 *  		- 要是 pattern 的当前字符匹配 txt 当前字符，移动双索引至下一个
	 *  		- 检查是否匹配完成，是则找到一个匹配的索引（当前索引减去 pattern 长度）
	 *  		- 要是 pattern 的当前字符不匹配 txt 当前字符，不需要从 pattern 头开始匹配，
	 *  		  只需要从 lps[j-1] 开始匹配。因为prefix 前部分可能和当前元素匹配。我们从 lap[j-1]
	 *  		  一直匹配直到 pattern 的第一个，若是还没有，则移动 i */
	
	public List<Integer> KMPSearch(String pat, String txt) {
		int M = pat.length(), N = txt.length();
		List<Integer> indices = new ArrayList<>();

		int lps[] = getLPS(pat);

		int i = 0, j = 0;
		while (i < N) {
			char p = pat.charAt(j), t = txt.charAt(i);
			if (p == t) { j++; i++; }	// 当前字符匹配，move
			
			if (j == M) {	// 找到一个匹配，
				indices.add(i - j);
				j = lps[j - 1];
			} else if (i < N && p != t) {	// 不匹配，和 prefix 上一个相比较
				if (j != 0) 	j = lps[j - 1];
				else 		i = i + 1;
			}
		}
		return indices;
	}
	
	/** 预处理返回字符串的 longest PROPER prefix which IS ALSO suffix!
	 * 例如对于字符串	
	 * "AAAA" 	- [0, 1, 2, 3].
	 * "ABCDE"	- [0, 0, 0, 0, 0]
	 * "AABAACAABAA" - [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]
	 * 
	 * 理解此算法主要是理解 len 不仅是 当前满足要求的串的长度，也是 prefix 的最后一个元素。算法做的就是
	 * 将现在的元素和 len 元素相对比(从 suffix 最后一个往前比较，不需要比较多个元素是因为前一步比较的结果
	 * 已经体现在了 len 上面了。所以如果当前元素和 len 元素相等的话，说明总共就有 len 个元素满足要求)，
	 * 要是相等就直接在 prefix 长度上加一。
	 * 若是不等，就 go back 回退到 lps[len - 1] 的地方继续做比较。为什么不尽量退到开始？因为现目前位置的
	 * prefix 的中间还有可能是相同的。简单来将就是当前字符和 len 做比较，要是相同就更新结果至 len + 1，要是
	 * 不想等，len 指针就回退到前一个有效的位置进行比较，要是前一个位置是 0，那么我们就直接回到起点。  */

	private int[] getLPS(String pat) {
		int len = 0, j = 1, M = pat.length();	// len - length of prefix, j - idx of lps
		int[] lps = new int[M];
		
		while (len < M && j < M) {
			if (pat.charAt(j) == pat.charAt(len)) {
				lps[j++] = 1 + len++;
			} else {
				if (len == 0) 	lps[j++] = 0;
				else 			len = lps[len - 1];
			}
		}
		return lps;
	}

	// Driver program to test above function
	public static void main(String args[]) {
		String txt = "ABABDABACDABABCABAB";
		String pat = "AB";
		System.out.println(new KMPStringMatching().KMPSearch(pat, txt).toString());
	}
}
