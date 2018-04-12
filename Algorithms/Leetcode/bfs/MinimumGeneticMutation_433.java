package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumGeneticMutation_433 {
	public int minMutation(String start, String end, String[] bank) {
		if (start.equals(end))
			return 0;

		Set<String> bankSet = new HashSet<>();
		for (String b : bank)
			bankSet.add(b);

		char[] charSet = new char[] {'A', 'C', 'G', 'T'};

		int level = 0;
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.offer(start);
		visited.add(start);

		while (!queue.isEmpty()) {
			int size = queue.size();
			
			while (size-- > 0) {
				String curr = queue.poll();		// pick one string from the queue
				if (curr.equals(end))						// find a match
					return level;

				char[] currArray = curr.toCharArray();			// separate the string to characters
				for (int i = 0; i < currArray.length; i++) {		// traverse the string and do the search every character
					char old = currArray[i];
					
					for (char c : charSet) {						// try every possibility
						currArray[i] = c;
						String next = new String(currArray);
						if (!visited.contains(next) && bankSet.contains(next)) {		// no acycle and there's 
							visited.add(next);
							queue.offer(next);
						}
					}
					
					currArray[i] = old;							// backtrack
				}
			}
			
			level++;
		}
		
		return -1;
	}
	
	
	public int minMutation1(String start, String end, String[] bank) {
		int n = bank.length + 1;

		String[] banks = new String[n];
		banks[0] = start;
		System.arraycopy(bank, 0, banks, 1, bank.length);

		int[] dis = new int[n];
		for (int i = 0; i < n; i++) {
			dis[i] = -1;
		}
		boolean[] vis = new boolean[n];
		dis[0] = 0;

		for (int i = 0; i < n; i++) {
			int minPos = 0;
			int minDis = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (!vis[j] && dis[j] != -1 && minDis > dis[j]) {
					minPos = j;
					minDis = dis[j];
				}
			}

			vis[minPos] = true;
			for (int j = 0; j < n; j++) {
				if (!vis[j] && canVisit(banks, minPos, j)) {
					if (dis[j] == -1)
						dis[j] = dis[minPos] + 1;
					else
						dis[j] = Math.min(dis[minPos] + 1, dis[j]);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (dis(banks[i], end) == 0)
				return dis[i];
		}
		return -1;
	}

	private boolean canVisit(String[] banks, int i, int j) {
		return dis(banks[i], banks[j]) == 1;
	}

	private int dis(String a, String b) {
		int cnt = 0;
		for (int p = 0; p < 8; p++) {
			if (a.charAt(p) != b.charAt(p))
				cnt++;
		}
		return cnt;
	}
}
