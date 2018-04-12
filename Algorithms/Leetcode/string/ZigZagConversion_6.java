package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ZigZagConversion_6 {
	public String convert(String s, int nRows) {
		char[] c = s.toCharArray();
		int N = c.length;
		
		// 建立 nRows 那么多个StringBuilder
		StringBuffer[] res = new StringBuffer[nRows];
		for (int i = 0; i < res.length; i++)
			res[i] = new StringBuffer();

		int i = 0;
		while (i < N) {		// 遍历所有的字符
			for (int down = 0; down < nRows && i < N; down++) // vertically down
				res[down].append(c[i++]);
			
			for (int up = nRows - 2; up >= 1 && i < N; up--) 	// obliquely up
				res[up].append(c[i++]);
		}
		
		for (int ptr = 1; ptr < res.length; ptr++)	// 对分好序的 zigzag 进行拼接到第一个 StringBuffer 成最终答案
			res[0].append(res[ptr]);
		
		return res[0].toString();
	}
}
