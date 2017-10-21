package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class AddBoldTagInString_616 {
	public String addBoldTag(String s, String[] dict) {
		List<int[]> list = new ArrayList<>();
		for (String d : dict) {
			for (int i = 0; i <= s.length() - d.length(); i++) {
				if (s.substring(i, i + d.length()).equals(d))
					list.add(new int[] {i, i + d.length() - 1});
			}
		}
		if (list.size() == 0)
			return s;
		Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		int start, prev = 0, end = 0;
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			res.append(s.substring(prev, list.get(i)[0]));
			start = i;
			end = list.get(i)[1];
			while (i < list.size() - 1 && list.get(i + 1)[0] <= end + 1) {
				end = Math.max(end, list.get(i + 1)[1]);
				i++;
			}
			res.append("<b>" + s.substring(list.get(start)[0], end + 1) + "</b>");
			prev = end + 1;
		}
		res.append(s.substring(end + 1, s.length()));
		return res.toString();
	}
}
