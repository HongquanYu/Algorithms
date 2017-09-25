package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlipGame2_294 {
    public static boolean canWin(String s) {
        HashMap<String, Boolean> h = new HashMap<>();
        int level = 0;
        return canWin(s.toCharArray(), h, level);
    }
    
    private static boolean canWin(char[] c, HashMap<String, Boolean> h, int level) {
		for (int i = 1; i < c.length; i++) {
			if (c[i] == '+' && c[i - 1] == '+') {
				System.out.println(" -------------------------------------------");
				c[i] = c[i - 1] = '-';				// if change to '-', see if there's a solution

				boolean nextPlayer;
				String key = String.valueOf(c);		// format to key
				System.out.print(level + "th  recursion: " + String.valueOf(c));
				System.out.println();
				
				if (!h.containsKey(key)) {
					nextPlayer = canWin(c, h, level + 1);
					h.put(key, nextPlayer);			// memoize subproblem solution
				} else {
					nextPlayer = h.get(key);
				}

				c[i] = c[i - 1] = '+';				// backtrack
				
				if (!nextPlayer)						// If next player cannot win, I win!!!
					return true;
			}
		}
            
        return false;
    }
    
	public boolean canWin2(String s) {
		s = s.replace('-', ' ');
		int G = 0;
		List<Integer> g = new ArrayList<>();
		for (String t : s.split("[ ]+")) {
			int p = t.length();
			if (p == 0)
				continue;
			while (g.size() <= p) {
				char[] x = t.toCharArray();
				int i = 0, j = g.size() - 2;
				while (i <= j)
					x[g.get(i++) ^ g.get(j--)] = '-';
				g.add(new String(x).indexOf('+'));
			}
			G ^= g.get(p);
		}
		return G != 0;
	}
	
	public static void main(String[] args) {
		String s = "---+++-+++-+";
		System.out.println("Original String " + s);
		System.out.println(canWin(s));
	}
}
