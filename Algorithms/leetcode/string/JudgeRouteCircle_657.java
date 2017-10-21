package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class JudgeRouteCircle_657 {
	public boolean judgeCircle(String moves) {
		int x = 0;
		int y = 0;
		for (char ch : moves.toCharArray()) {
			if (ch == 'U')
				y++;
			else if (ch == 'D')
				y--;
			else if (ch == 'R')
				x++;
			else if (ch == 'L')
				x--;
		}
		return x == 0 && y == 0;
	}
}
