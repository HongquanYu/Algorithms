package twoPointers;

/** @author: Hongquan Yu
 *  @date: Feb 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class ContainerWithMostWater_11 {
	
	/** Brute Force Solution */
	
	public int maxArea(int[] height) {
		int maxarea = 0;
		for (int i = 0; i < height.length; i++)
			for (int j = i + 1; j < height.length; j++)
				maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
		
		return maxarea;
	}
	
	/** Linear Solution */
	
    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        
        while (l <= r) {
            int t = Math.min(height[l], height[r]);
            max = Math.max(max, t * (r - l));
            if (height[l] < height[r])  l++;
            else                        r--;
        }
        
        return max;
    }
}
