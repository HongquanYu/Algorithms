package greedy;

/** @author: Hongquan Yu
 *  @date: Feb 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CouplesHoldingHands_765 {
	
	/** Greedy Solution */
	
	public int minSwapsCouples(int[] row) {
		int n = row.length;
		int[] pos = new int[n];
		
		for (int i = 0; i < n; i++)
			pos[row[i]] = i;
		
		int minCount = 0;
		for (int i = 0; i < n; i += 2) {		// deal with every couple!
			int j = row[i] % 2 == 0 ? row[i] + 1 : row[i] - 1;	// 找到他的伴侣是哪个，偶数是下一个，奇数是上一个
			if (row[i + 1] != j) {		// 如果 i 的伴侣不是应该的伴侣，那么就应该交换
				swap(row, i + 1, pos, pos[j]);	// 交换
				minCount++;
			}
		}
		
		return minCount;
	}
	
	/**  */
	private void swap(int[] row, int x, int[] pos, int y) {
		int temp = row[x];
		pos[temp] = y;
		pos[row[y]] = x;
		row[x] = row[y];
		row[y] = temp;
	}

	/** Greedy Solution */
    
	public int minSwapsCouples2(int[] row) {
		int res = 0, N = row.length;

		int[] ptn = new int[N];
		int[] pos = new int[N];

		for (int i = 0; i < N; i++) {
			ptn[i] = (i % 2 == 0 ? i + 1 : i - 1);
			pos[row[i]] = i;
		}

		for (int i = 0; i < N; i++) {
			for (int j = ptn[pos[ptn[row[i]]]]; i != j; j = ptn[pos[ptn[row[i]]]]) {
				swap(row, i, j);
				swap(pos, row[i], row[j]);
				res++;
			}
		}

		return res;
	}

	private void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
