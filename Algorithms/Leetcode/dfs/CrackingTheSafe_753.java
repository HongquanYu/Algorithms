package dfs;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Apr 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class CrackingTheSafe_753 {
	
	
	/** 总共的 lock 数量是 k^n.
	 * 然后我们的目标是把这些 codes 尽可能短的放进一个 string 里。
	 * 所以我们能做到的最大就是复用后面的 n-1位！  */
	
	public String crackSafe(int n, int k) {
		StringBuilder sb = new StringBuilder();
		int total = (int) (Math.pow(k, n));
		
		for (int i = 0; i < n; i++)
			sb.append('0');

		Set<String> visited = new HashSet<>();
		visited.add(sb.toString());

		dfs(sb, total, visited, n, k);

		return sb.toString();
	}

	private boolean dfs(StringBuilder sb, int goal, Set<String> visited, int n, int k) {
		if (visited.size() == goal)
			return true;
		String prev = sb.substring(sb.length() - n + 1, sb.length());
		for (int i = 0; i < k; i++) {
			String next = prev + i;
			if (!visited.contains(next)) {
				visited.add(next);
				sb.append(i);
				if (dfs(sb, goal, visited, n, k))
					return true;
				else {
					visited.remove(next);
					sb.delete(sb.length() - 1, sb.length());
				}
			}
		}
		return false;
	}
}
