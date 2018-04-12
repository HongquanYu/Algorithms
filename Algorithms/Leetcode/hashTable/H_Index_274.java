package hashTable;

import java.util.Arrays;

public class H_Index_274 {
	
	/** Most Concise Solution */
	
	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		
		int i = 0, N = citations.length;
		// 从后向前依次检查元素，
		while (i < N && citations[N - 1 - i] > i)
			i++;
		
		return i; // after the while loop, i = i + 1
	}
	
	/** Very Intuitive Solution */
	
	public int hIndex1(int[] citations) {
		int N = citations.length;
		if (N == 0) 	return 0;

		int[] index = new int[N + 1];	// 索引 i 代表被引用 i 次的文章数是多少
		// 遍历并将当前的
		for (int i = 0; i < N; i++) {
			if (citations[i] > N)  	index[N] += 1;				// 当前文章引用超过文章数N，记录到 index[N]
			else 					index[citations[i]] += 1;	// 被引用的次数的文章 + 1
		}
		
		int sum = 0;
		for (int i = N; i >= 0; i--) {	// 从后向前来遍历并累加最高的引用至向下
			sum = sum + index[i];
			if (sum >= i) return i;
		}
		
		return 0;
	}
}
