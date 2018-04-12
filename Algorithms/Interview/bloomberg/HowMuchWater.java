package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class HowMuchWater {
	public int waterSize(int[] nums) {
		int sum = 0, N = nums.length;
		int[] left = new int[N];			// 记录当前节点 i 左边的最大节点
		int[] right = new int[N];		// 记录当前节点 i 右边的最大节点
		
		int high = nums[0];
		for (int i = 0; i < N; ++i) {
			high = Math.max(high, nums[i]);
			left[i] = high;
		}
		
		high = nums[N - 1];
		for (int i = N - 1; i >= 0; --i) {
			high = Math.max(high, nums[i]);
			right[i] = high;
		}
		
		// 计算能容纳多少：对于 i，都是和他邻居 i + 1 来决定装多少水的，他邻居的高度又是取决于
		// 上面左右数组中较小的那个！
		for (int i = 0; i < N - 1; ++i) {
			sum += Math.min(Math.min(left[i], right[i]), Math.min(left[i + 1], right[i + 1]));
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		int[] num = new int[] {1, 2, 3, 2, 3, 1};
		int[] num2 = new int[] {3, 2, 4, 1, 3, 7, 2};
		
		HowMuchWater h = new HowMuchWater();
		
		System.out.println(h.waterSize(num));
		System.out.println(h.waterSize(num2));
	}
}
