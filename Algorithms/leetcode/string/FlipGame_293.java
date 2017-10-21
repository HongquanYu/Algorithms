package string;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FlipGame_293 {
	public List<String> generatePossibleNextMoves(String s) {
		List<String> res = new ArrayList<String>();
		if (s.length() < 2)
			return res;

		if (s.substring(0, 2).equals("++"))
			res.add("--" + s.substring(2));

		for (int i = 1; i <= s.length() - 2; ++i) {
			if (s.substring(i, i + 2).equals("++"))
				res.add(s.substring(0, i) + "--" + s.substring(i + 2));
		}

		return res;
	}
}