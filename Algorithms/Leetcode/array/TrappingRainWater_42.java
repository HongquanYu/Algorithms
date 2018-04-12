package array;

/* The intuition is to trace the max height from left and right sides!
 * brute force solution!
 */

public class TrappingRainWater_42 {
	public int trap(int[] A) {
		
		int left = 0, right = A.length - 1;
		int maxL = 0, maxR = 0;
		int res = 0;
		
		while (left <= right) {
			maxL = Math.max(maxL, A[left]);
			maxR = Math.max(maxR, A[right]);
			
			if (maxL < maxR) {		// maxL is smaller than maxR, so the (maxL - A[a]) water can be stored
				res += (maxL - A[left]); 
				left++;
			} else {
				res += (maxR - A[right]);
				right--;
			}
		}
		
		return res;
	}
	
	public int trap2(int[] height) {
		if (height == null || height.length == 0)
			return 0;
		int s = 0, e = height.length - 1;
		int leftMax = 0, rightMax = 0;
		int res = 0;

		while (s <= e) {
			if (height[s] < height[e]) {
				res += height[s] < leftMax ? leftMax - height[s] : 0;
				leftMax = Math.max(leftMax, height[s]);
				s++;
			} else {
				res += height[e] < rightMax ? rightMax - height[e] : 0;
				rightMax = Math.max(rightMax, height[e]);
				e--;
			}
		}
		return res;
	}
}
