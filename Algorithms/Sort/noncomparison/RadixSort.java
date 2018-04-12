package noncomparison;

/** @author: Hongquan Yu
 *  @date: Feb 4, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: N+R, Time: WN, Worst: WN. R is Radix, W is widest word size! */

public class RadixSort {
	private int RADIX = 10, MIN, MAX;

	public void sort(int[] nums) {
		MIN = nums[0]; MAX = nums[0];
		for (int num : nums) {
			if (num < MIN) 			MIN = num;
			else if (num > MAX) 		MAX = num;
		}

		// Perform counting sort on each exponent/digit, starting at the LSD
		int exponent = 1;		// 从 1，10，100，1000
		while ((MAX - MIN) / exponent >= 1) {
			countingSortByDigit(nums, exponent);
			exponent *= RADIX;
		}
	}

	private void countingSortByDigit(int[] nums, int exponent) {
		int idx, N = nums.length;
		int[] buckets = new int[RADIX];			// Radix 那么多个桶，Bucket is initialized to 0
		int[] output = new int[N];

		for (int n : nums) {						// Count frequencies
			idx = ((n - MIN) / exponent) % RADIX;
			buckets[idx]++;
		}
		for (int i = 1; i < RADIX; i++)			// frequency数组累加起来
			buckets[i] += buckets[i - 1];

		for (int i = N - 1; i >= 0; i--) {		// Move records
			idx = ((nums[i] - MIN) / exponent) % RADIX;
			output[--buckets[idx]] = nums[i];
		}

		for (int i = 0; i < N; i++)				// Copy back
			nums[i] = output[i];
		System.out.print("当前digit " + exponent +" 的排序：	");
		print(nums);
	}
	
	static void print(int arr[]) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + ", ");
		System.out.println();
	}

	public static void main(String[] args) {
		RadixSort rs = new RadixSort();
		int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
		rs.sort(arr);
		System.out.println("----------------------------------------------------------");
		System.out.print("排序结果：		");
		print(arr);
	}
}
