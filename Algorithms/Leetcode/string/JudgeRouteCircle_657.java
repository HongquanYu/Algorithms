package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class JudgeRouteCircle_657 {
	public boolean judgeCircle(String moves) {
		int x = 0, y = 0;
		
		for (char ch : moves.toCharArray()) {
			switch (ch) {
				case 'U':
					y++;
					break;
				case 'D':
					y--;
					break;
				case 'R':
					x++;
					break;
				case 'L':
					x--;
					break;
			}
		}
		
		return x == 0 && y == 0;
	}
}
