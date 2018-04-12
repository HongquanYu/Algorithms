package math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SelfDividingNumbers_728 {
	
	/** Brute Force Solution! */
	
	public List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> res = new LinkedList<>();

		for (int i = left; i <= right; ++i) {
			if (isSelfDividing(i))
				res.add(i);
		}
		return res;
	}

	private boolean isSelfDividing(int num) {
		int original = num;
		while (num != 0) {
			int last = num % 10;
			if (last == 0 || original % last != 0)
				return false;
			num /= 10;
		}
		return true;
	}
	
	/** More Concise Solution! */
	
	public List<Integer> selfDividingNumbers2(int left, int right) {
		List<Integer> list = new ArrayList<>();
		
		for (int i = left; i <= right; i++) {
			int j = i;
			for (; j > 0; j /= 10) {
				if ((j % 10 == 0) || (i % (j % 10) != 0))
					break;
			}
			if (j == 0) 	list.add(i);
		}
		return list;
	}
}
