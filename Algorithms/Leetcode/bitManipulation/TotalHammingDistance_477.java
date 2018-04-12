package bitManipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TotalHammingDistance_477 {

	/** 依次求每一位上所有数的distance，然后再将其加起来。 */
	
	public int totalHammingDistance(int[] nums) {
		int total = 0, N = nums.length;
		
		for (int j = 0; j < 32; j++) {
			int bitCount = 0;
			for (int i = 0; i < N; i++)
				bitCount += (nums[i] >> j) & 1;
			total += bitCount * (N - bitCount);
		}
		return total;
	}
}
