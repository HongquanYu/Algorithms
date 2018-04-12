package string;

import java.util.ArrayList;
import java.util.List;

	/* Follow up question LeetCode 294 is at backtracking section */

public class FlipGame1_293 {
	
	/* My solution, verbose */
	
    public static List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() < 2)
        		return res;
        
        if (s.substring(0, 2).equals("++"))
			res.add("--" + s.substring(2));
        
        for (int i = 1; i <= s.length() - 2; ++i) {
        		if (s.substring(i, i + 2).equals("++"))
        			res.add(s.substring(0, i) + "--" + s.substring(i + 2));
        }
        
        return res;
    }
    
    /* A more concise solution */
    
	public static List<String> generatePossibleNextMoves1(String s) {
		List<String> list = new ArrayList<>();
		for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; )
			list.add(s.substring(0, i) + "--" + s.substring(i + 2));
		return list;
	}
    
    public static void main(String[] args) {
    		String s = "---+++-+++-+";
    		System.out.print("String: \t\t" + s);
    		System.out.println();
    		System.out.print("Test: \t\t" + generatePossibleNextMoves(s));
    		System.out.println();
    		System.out.print("Expected: \t" + generatePossibleNextMoves1(s));
    }
}
