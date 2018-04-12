package array;

/* The intuition is to get the largest area which is computed by equation: area = height * length!
 * We get the maximum along the way of computing! Initialize the maximum with the product of 
 * the widest container and the smaller one of height! We step in by move the index of lower height
 * side.  */

public class ContainerWithMostWater_11 {
	public int maxArea(int[] height) {
		
		int maxarea = 0, l = 0, r = height.length - 1;
		
		while (l < r) {
			maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
			if (height[l] < height[r])
				l++;
			else
				r--;
		}
		
		return maxarea;
	}
}
