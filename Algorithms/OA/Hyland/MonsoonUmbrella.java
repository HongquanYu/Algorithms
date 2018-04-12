package Hyland;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MonsoonUmbrella {
	public int getUmbrellas(int n, int[] p) {
		Arrays.sort(p);
		int N = p.length;
		int[] com = new int[] { n, 0 };
		if (dfs(com, p, N - 1))
			return com[1];
		return -1;
	}

	private boolean dfs(int[] com, int[] p, int s) {
		if (com[0] < 0)
			return false;
		else if (com[0] == 0)
			return true;

		int i;
		for (i = s; i >= 0; --i) {
			if (p[i] <= com[0]) {
				com[0] = p[i];
				com[1]++;
				if (dfs(com, p, s))
					return true;
			}
		}
		return i != 0;
	}
}
