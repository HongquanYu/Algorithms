package pureStorage;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/** @author: Hongquan Yu
 *  @date: Jan 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class OA_8 {
	public int checkSequence(String[] events) {
		if (events == null || events.length < 1)
			return 0;
		
		int step = 1;
		Stack<String> lock = new Stack<String>();
		Set<String> records = new HashSet<String>();
		
		for (String l : events) {
			String[] array = l.split(" ");
			if (array[0].equals("ACQUIRE")) {	// 当前是个acquire
				if (records.contains(array[1]))
					return step;
				
				records.add(array[1]);
				lock.push(array[1]);
			} else {
				if (lock.isEmpty() || !lock.peek().equals(array[1]))
					return step;
				
				lock.pop();
				records.remove(array[1]);
			}
			step++;
		}
		if (!lock.isEmpty())
			return events.length + 1;
		
		return 0;
	}
	
    public static void main(String[] args) {
    	OA_8 ob = new OA_8();
        
        String[] inputs1 = new String[]{"ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364"};
        int res1 = ob.checkSequence(inputs1);
        System.out.println("Expected output: 0");
        System.out.println("My output: " + res1);
        System.out.println();
        
        String[] inputs2 = new String[]{"ACQUIRE 364", "ACQUIRE 84", "RELEASE 364", "RELEASE 84"};
        int res2 = ob.checkSequence(inputs2);
        System.out.println("Expected output: 3");
        System.out.println("My output: " + res2);
        System.out.println();
        
        String[] inputs3 = new String[]{"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364", "ACQUIRE 456"};
        int res3 = ob.checkSequence(inputs3);
        System.out.println("Expected output: 7");
        System.out.println("My output: " + res3);
        System.out.println();
        
        String[] inputs4 = new String[]{"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84",
                        "RELEASE 364", "ACQUIRE 789", "RELEASE 456", "RELEASE 123"};
        int res4 = ob.checkSequence(inputs4);
        System.out.println("Expected output: 7");
        System.out.println("My output: " + res4);
        System.out.println();
        
        String[] inputs5 = new String[]{"ACQUIRE 364", "ACQUIRE 84", "ACQUIRE 364", "RELEASE 364"};
        int res5 = ob.checkSequence(inputs5);
        System.out.println("Expected output: 3");
        System.out.println("My output: " + res5);
        System.out.println();
}
}
