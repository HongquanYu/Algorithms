package array;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SummaryRanges_228 {
	
	/** 自己的解 */
	
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return res;
		int start = 0, ptr = start;

		for (int i = 1; i <= nums.length; ++i) {
			if (i == nums.length || nums[i] > nums[ptr] + 1) {
				if (ptr == start)
					res.add("" + nums[start]);
				else
					res.add(nums[start] + "->" + nums[ptr]);
				start = ptr = i;
			} else
				ptr++;
		}
		return res;
	}
	
	/** 高票答案 */
	
	public List<String> summaryRanges1(int[] nums) {
		List<String> list = new ArrayList<>();
		
		if (nums.length == 1) {
			list.add(nums[0] + "");
			return list;
		}
		for (int i = 0; i < nums.length; i++) {
			int a = nums[i];
			while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1)
				i++;
			
			if (a != nums[i]) 	list.add(a + "->" + nums[i]);
			else 				list.add(a + "");
		}
		return list;
	}
    
    public static void main(String[] args) {
		int[] tmp = new int[]{0,1,2,4,5,7};
		int[] tmp1 = new int[]{0, 1, 2, 4, 5, 7, 9, 10, 11, 13};
		
		SummaryRanges_228 s = new SummaryRanges_228();
		System.out.println(s.summaryRanges(tmp).toString());
		System.out.println(s.summaryRanges(tmp1).toString());
	}
}
