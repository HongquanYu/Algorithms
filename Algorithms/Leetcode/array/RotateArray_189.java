package array;

/** @author: Hongquan Yu
 *  @date: Feb 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RotateArray_189 {
	
	/** Concise Solution, Time: O(n), Space: O(1) */
	
	public void rotate(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);		// 逆转 整个数组
		reverse(nums, 0, k - 1);					// 逆转前 K 个数
		reverse(nums, k, nums.length - 1);		// 再逆转后面 N - K 个数
	}

	public void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	
	/** Time: O(n), Space: O(k % nums.length) */
	
	public void rotate2(int[] nums, int k) {
		int N = nums.length;
		if (N <= 1) 	return;
		
		// step each time to move
		int step = k % N;
		int[] tmp = new int[step];
		
		for (int i = 0; i < step; i++)		// 顺序拷贝要逆转的 K 个元素到额外的空间
			tmp[i] = nums[N - step + i];
		
		for (int i = N - step - 1; i >= 0; i--)		// 复制剩下来的元素，逆序拷贝到数组末尾
			nums[i + step] = nums[i];
		
		for (int i = 0; i < step; i++)		// 数组的前k个空间从辅助数组拷贝过来
			nums[i] = tmp[i];
	}
	
	/** Time: O(n), Space: O(1) */
	
	public void rotate3(int[] nums, int k) {
		if (nums.length <= 1)
			return;

		int step = k % nums.length;
		int gcd = findGCD(nums.length, step);
		int position, count;

		for (int i = 0; i < gcd; i++) {
			position = i;
			count = nums.length / gcd - 1;
			for (int j = 0; j < count; j++) {
				position = (position + step) % nums.length;
				// swap index value in index i and position
				nums[i] ^= nums[position];
				nums[position] ^= nums[i];
				nums[i] ^= nums[position];
			}
		}
	}
    
    public int findGCD(int a, int b){
        return (a == 0 || b == 0) ? a + b : findGCD(b, a % b);
    }
}
