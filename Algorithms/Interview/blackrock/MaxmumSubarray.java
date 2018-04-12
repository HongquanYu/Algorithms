package blackrock;

import java.util.stream.Stream;

/** @author: Hongquan Yu
 *  @date: Mar 7, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaxmumSubarray {
    public int maxSubArray(int[] nums) {
    		int sum = 0;
    		int max = Integer.MIN_VALUE;
    		
        for (int i = 0; i < nums.length; ++i) {
        		sum = sum > 0 ? sum + nums[i] : nums[i];
        		max = Math.max(max, sum);
        }
        
        return max;
    }
    
    public static void main(String[] args) {
		int[] array = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		
		String s = "-10, 2, 3, -2, 0, 5, -15";
		String[] a = s.split("[,\\s]\\s*");
		int[] t = Stream.of(a).mapToInt(Integer::parseInt).toArray();
		
		for (int i : t)
			System.out.print(i + ",");
		
		MaxmumSubarray m = new MaxmumSubarray();
		System.out.println(m.maxSubArray(array));
	}
}
