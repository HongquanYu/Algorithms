package uncategorized;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RangeAddition_370 {
	
	/** 不用把 range 里面的每一个都相加，可以优化为：
	 * 起点加上变化的数，然后终点 + 1 减去加的数字。就可以做数组从前到后的累加 */
	
	public int[] getModifiedArray(int N, int[][] updates) {
		int[] res = new int[N];
		if (updates == null || updates.length == 0)
			return res;

		for (int i = 0; i < updates.length; i++) {
			res[updates[i][0]] += updates[i][2]; // 更新起点
			
			if (updates[i][1] < N - 1) // 若是中点没到最后一个，则终点的下一个减去
				res[updates[i][1] + 1] -= updates[i][2];
		}

		for (int i = 1; i < N; i++)
			res[i] += res[i - 1];

		return res;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1,  3,  2}, {2,  4,  3}, {0,  2, -2}};
		
		System.out.println(Arrays.toString(new RangeAddition_370().getModifiedArray(5, matrix)));
	}
}
