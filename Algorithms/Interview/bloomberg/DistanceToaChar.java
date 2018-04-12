package bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DistanceToaChar {
	public int[] distance(String s, char c) {
		char[] string = s.toCharArray();
		int[] dis = new int[s.length()];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		int idx = s.indexOf(c, 0);
		List<Integer> chars = new ArrayList<>();
		
		while (idx != -1) {
			chars.add(idx);
			idx = s.indexOf(c, idx + 1);
		}
		for (int i = 0; i < string.length; ++i) {
			for (int index : chars) {
				dis[i] = Math.min(dis[i], Math.abs(i - index));
			}
		}
		
		return dis;
	}
	
	public static void main(String[] args) {
		DistanceToaChar d = new DistanceToaChar();
		
		System.out.println(Arrays.toString(d.distance("BLOOMBERG", 'B')));
	}
}
