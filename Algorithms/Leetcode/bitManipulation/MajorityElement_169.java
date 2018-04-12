package bitManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MajorityElement_169 {
	
	// Sorting
	public int majorityElement1(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}

	// Hashtable
	public int majorityElement2(int[] nums) {
		
		Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		int ret = 0;
		
		for (int num : nums) {
			if (!myMap.containsKey(num))
				myMap.put(num, 1);			// initial put it for 1 occurrence
			else
				myMap.put(num, myMap.get(num) + 1);		// add 1 occurrence
			
			if (myMap.get(num) > nums.length / 2) {		// occurrence has more than half of numbers, majority element is!
				ret = num;
				break;
			}
		}
		
		return ret;
	}

	// Moore voting algorithm
	public int majorityElement3(int[] nums) {
		int count = 0, ret = 0;
		for (int num : nums) {
			if (count == 0)
				ret = num;
			if (num != ret)
				count--;
			else
				count++;
		}
		
		return ret;
	}

	// Bit manipulation
	public int majorityElement(int[] nums) {
		
		int[] bit = new int[32];
		
		// Count number of '1's every bit of all numbers
		for (int num : nums)			// 32 N computation
			for (int i = 0; i < 32; i++)
				if ((num >> (31 - i) & 1) == 1)	// Record
					bit[i]++;
		
		int ret = 0;
		
		for (int i = 0; i < 32; i++) {		// 
			bit[i] = bit[i] > nums.length / 2 ? 1 : 0;	// If half of numbers are set in one bit, then it should be in majority element
			ret += bit[i] * (1 << (31 - i));
		}
		
		return ret;
	}
}
