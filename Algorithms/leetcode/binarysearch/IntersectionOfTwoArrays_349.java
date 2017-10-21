package binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class IntersectionOfTwoArrays_349 {
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Arrays.sort(nums2);

		for (Integer num : nums1)
			if (Arrays.binarySearch(nums2, num) > 0)
				set.add(num);

		int i = 0;
		int[] result = new int[set.size()];
		for (Integer num : set)
			result[i++] = num;
		
		return result;
	}
}
