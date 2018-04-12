package ixlLearning;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class JungleBook {
	
	/** 思想是：最少能最少分多少组就是找到这里面树最高高度，至少要将最高高度分开才能保证一个组里面没有捕食关系。
	 * 遍历数组，建立树的高度，然后最高的那棵树必须被分开，这样能保证连最高的树里面也没有捕食关系。 */
	
	public int maxHeight(int[] predators) {
		int N = predators.length;
		int[] treeHeight = new int[N];
		
		Arrays.fill(treeHeight, 1);
		for (int i = 0; i < N; ++i) {
			int cur = predators[i];
			
			if (cur != -1) {		// 如果不是树根
				int pre = i;
				while (cur != -1) {	// 找到当前树的根
					treeHeight[cur] = Math.max(treeHeight[cur], treeHeight[pre] + 1);
					pre = cur;
					cur = predators[cur];
				}
			}
		}
		System.out.println(Arrays.toString(treeHeight));
		int max = 0;
		for (int n : treeHeight)
			max = Math.max(max, n);
		return max;
	}
	
	public static void main(String[] args) {
		int[] pred = new int[] { 1, -1, 3, 1 };
		int[] pred1 = new int[] { -1, 0, 1 };
		
		System.out.println(new JungleBook().maxHeight(pred));
		System.out.println(new JungleBook().maxHeight(pred1));
	}
}
