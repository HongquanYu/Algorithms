package greedy;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate_649 {
	public String predictPartyVictory(String senate) {
		Queue<Integer> q1 = new LinkedList<Integer>();		// index of one party
		Queue<Integer> q2 = new LinkedList<Integer>();
		int n = senate.length();
		
		for (int i = 0; i < n; i++) {						// remember all indices of parties
			if (senate.charAt(i) == 'R')
				q1.add(i);
			else
				q2.add(i);
		}
		
		while (!q1.isEmpty() && !q2.isEmpty()) {				// fighting of two parties
			int r_index = q1.poll(), d_index = q2.poll();
			if (r_index < d_index)				// R kills D one senator, 
				q1.add(r_index + n);
			else									// D kills R one senator, 
				q2.add(d_index + n);
		}
		return (q1.size() > q2.size()) ? "Radiant" : "Dire";
	}
	
	public String predictPartyVictory1(String senate) {
		int r = 0, d = 0, start = 0;
		char[] arr = senate.toCharArray();
		for (char c : arr) {
			if (c == 'R') 	r++;
			else 			d++;
		}

		while (r > 0 && d > 0) {
			while (arr[start] != 'R' && arr[start] != 'D') {
				start = (start + 1) % arr.length;
			}

			char ban = 'R';
			if (arr[start] == 'R') {
				ban = 'D';
				d--;
			} else {
				r--;
			}
			int idx = (start + 1) % arr.length;
			while (arr[idx] != ban) {
				idx = (idx + 1) % arr.length;
			}

			arr[idx] = ' ';
			start = (start + 1) % arr.length;
		}

		return d == 0 ? "Radiant" : "Dire";
	}
}
