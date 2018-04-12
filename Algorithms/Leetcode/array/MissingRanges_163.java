package array;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MissingRanges_163 {
	
	/** 用一个指针prev记录上次range的结尾，一个指针curr记录当前遍历到的数字，如果curr和prev相差大于1，
	 * 说明一个missing range，我们将其加入结果列表中就行了。注意 corner cases */
	
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new LinkedList<>();
		int prev = lower - 1, curr = 0;
		int N = nums.length;
		
		for (int i = 0; i <= N; i++) {		// 循环 N + 1 次
			curr = (i == N) ? upper + 1 : nums[i];
			if (curr > prev + 1)
				res.add(buildRange(prev + 1, curr - 1));
			
			prev = curr;
		}
		return res;
	}

	private String buildRange(int from, int to) {
		return from == to ? String.valueOf(from) : from + "->" + to;
	}
	
	public static void main(String[] args) {
		MissingRanges_163 m = new MissingRanges_163();
		int [] tmp = new int[] {0, 1, 3, 50, 75};
		System.out.println(m.findMissingRanges(tmp, 0, 99).toString());
	}
}
