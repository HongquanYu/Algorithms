package binarysearch;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Heaters_475 {
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int result = Integer.MIN_VALUE;

		for (int house : houses) {
			int index = Arrays.binarySearch(heaters, house);
			if (index < 0) {
				index = -(index + 1);
			}
			int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
			int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

			result = Math.max(result, Math.min(dist1, dist2));
		}

		return result;
	}
    
	/* Two pointers */
    
	public int findRadius2(int[] houses, int[] heaters) {
		Arrays.sort(houses);
		Arrays.sort(heaters);

		int i = 0, res = 0;
		for (int house : houses) {
			while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
				i++;
			}
			res = Math.max(res, Math.abs(heaters[i] - house));
		}

		return res;
	}
}
