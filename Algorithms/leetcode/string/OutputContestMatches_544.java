package string;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class OutputContestMatches_544 {
	public String findContestMatch(int n) {
		List<String> matches = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			matches.add(String.valueOf(i));

		while (matches.size() != 1) {
			List<String> newRound = new ArrayList<>();
			for (int i = 0; i < matches.size() / 2; i++)
				newRound.add(
						"(" + matches.get(i) + "," + matches.get(matches.size() - i - 1) + ")");
			matches = newRound;
		}
		
		return matches.get(0);
	}
}
