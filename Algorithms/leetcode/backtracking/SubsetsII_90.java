package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SubsetsII_90 {
	public List<List<Integer>> subsetsWithDup(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> empty = new ArrayList<Integer>();
		result.add(empty);
		Arrays.sort(num);

		for (int i = 0; i < num.length; i++) {
			int dupCount = 0;
			while (((i + 1) < num.length) && num[i + 1] == num[i]) {
				dupCount++;
				i++;
			}
			int prevNum = result.size();
			for (int j = 0; j < prevNum; j++) {
				List<Integer> element = new ArrayList<Integer>(result.get(j));
				for (int t = 0; t <= dupCount; t++) {
					element.add(num[i]);
					result.add(new ArrayList<Integer>(element));
				}
			}
		}
		
		return result;
	}
}
