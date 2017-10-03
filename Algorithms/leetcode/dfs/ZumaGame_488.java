package dfs;

public class ZumaGame_488 {
	private int MAXCOUNT = 6;   // the max balls you need will not exceed 6 since "The number of balls in your hand won't exceed 5"

	public int findMinStep(String board, String hand) {
	    int[] handCount = new int[26];
	    
	    // Count every character in the alphabet to handCount
	    for (int i = 0; i < hand.length(); ++i) 
	    		++handCount[hand.charAt(i) - 'A'];
	    
	 // append a "#" to avoid special process while j == board.length, make the code shorter.
	    int rs = backtrack(board + "#", handCount);  
	    return rs == MAXCOUNT ? -1 : rs;
	}
	
	private int backtrack(String s, int[] h) {
	    s = removeConsecutive(s);     					// Initialize to non 3 consecutive character string
		
	    if (s.equals("#"))		// empty string
			return 0;
	    
		int rs = MAXCOUNT, need = 0;
		for (int i = 0, j = 0; j < s.length(); ++j) {
			if (s.charAt(j) == s.charAt(i))
				continue;
			need = 3 - (j - i); 			// balls need to remove current consecutive balls.
			
			if (h[s.charAt(i) - 'A'] >= need) {		// There is enough characters to be used to remove current consecutive string
				h[s.charAt(i) - 'A'] -= need;
				rs = Math.min(rs, need + backtrack(s.substring(0, i) + s.substring(j), h));	// DFS: Get a solution and track if it is minimum
				h[s.charAt(i) - 'A'] += need;		// backtrack
			}
			
			i = j;		// jump over the gap and start to a new checking process
		}
		
	    return rs;
	}
	
	//Use two pointers to remove consecutive balls longer than 3. Recursion.
	private String removeConsecutive(String board) {
		for (int i = 0, j = 0; j < board.length(); ++j) {
			if (board.charAt(j) == board.charAt(i))		// find the longest substring of current string 
				continue;
			if (j - i >= 3)								// if the consecutive substring is longer than 3, remove current substring
				return removeConsecutive(board.substring(0, i) + board.substring(j));
			else											// not exceed, just skip it
				i = j;
		}
		
		return board;
	}
}
