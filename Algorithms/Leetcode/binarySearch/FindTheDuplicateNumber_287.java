package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class FindTheDuplicateNumber_287 {
	
	/** 可以转化为 Floyd's Tortoise and Hare 问题：
	 * 我们将数组 nums 中的 index i 和其值 nums[i] 看成是一个 pair，“next” 指针是通过索引 nums[i]
	 * 指向下一个数，也就是下一个数为 nums[ nums[i] ]。 
	 * 
	 * 首先，证明 cycle 存在，在 1 到 n 的索引当中，一定有一个重复的指针（重复值）。
	 * 另外，0 不会出现在数组里，所以 nums[0] 不会是 cycle 的一部分。 从 nums[0] 开始遍历数组就像
	 * 是遍历一个带环的链表似的。 因此，我们可以使用 */
	
	public static int findDuplicate(int[] nums) {
		int slow = nums[0];			// slow 指针指向环外的第一个节点，首节点
		int fast = nums[nums[0]];	// 第二个节点，
		
		while (slow != fast) {	// slow 一次跑一步，fast 一次跑两步
			slow = nums[slow];
			fast = nums[nums[fast]];
		}

		fast = 0;
		while (fast != slow) {	// slow 和 fast 都是一次跑一步，直到他们有相同值
			fast = nums[fast];
			slow = nums[slow];
		}
		
		return slow;
	}
	
	/** The array is UN-SORTED !!! 
	 * 也可以用二分搜索，根据鸽笼原理来做！
	 * 
	 * 我们取得搜索范围的中间，遍历数组找到小于等于这个数的元素个数，然后根据鸽笼原理决定重复数字是在哪一边，
	 * 就对这个区间进行再搜索！直到我们搜索到两个 boundaries 交叉.
	 * 我们为什么要返回两个 lo 和 hi 中间的 lo 呢？ 
	 * 根据算法特性，先到达重复值的总是 lo 指针，要结束循环，hi 总要变成 lo - 1。所以，hi 就成了比重复值
	 * 小 1 的那么一个数，所以，我们需要返回 lo 或者 hi + 1. */
	
	public static int findDuplicate1(int[] nums) {
		int lo = 1, hi = nums.length - 1;	// 初始化index搜索区间[1, n-1]
		
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;	
			int cnt = 0;
			for (int a : nums) 		// 遍历数组，计数比mid小的元素
				if (a <= mid)	 ++cnt;
			
			// one hole for one pigeon
			if (cnt <= mid) 	lo = mid + 1;	// 当前数组里比中点小的数少，说明重复的数在另一半
			else 			hi = mid - 1;
		}
//		System.out.println("High: " + hi);
//		System.out.println("Low: " + lo);
		
		return lo;
	}
	
	public static void main(String [] args) {
		int [] a = new int[] {1, 2, 3, 4, 5, 6, 6, 7};
		int [] b = new int[] {1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int [] c = new int[] {1, 1};
		int [] d = new int[]{1, 3, 4, 2, 2};
		
		System.out.println(" -- res: " + findDuplicate1(a));
		System.out.println(" -- res: " + findDuplicate1(b));
		System.out.println(" -- res: " + findDuplicate1(c));
		System.out.println(" -- res: " + findDuplicate1(d));
	}
}
