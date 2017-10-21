package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TwoSum3_DataStructureDesign_170 {
	Set<Integer> sum;
	Set<Integer> num;

	TwoSum3_DataStructureDesign_170() {
		sum = new HashSet<Integer>();
		num = new HashSet<Integer>();
		hm = new HashMap<Integer,Integer>();	// second solution
	}

	// Add the number to an internal data structure.
	public void add(int number) {
		if (num.contains(number)) {
			sum.add(number * 2);
		} else {
			Iterator<Integer> iter = num.iterator();
			while (iter.hasNext()) {
				sum.add(iter.next() + number);
			}
			num.add(number);
		}
	}

	// Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		return sum.contains(value);
	}
	
	/* Another solution from different perspective */
	
    Map<Integer,Integer> hm;

    // Add the number to an internal data structure.
	public void add1(int number) {
	    if(hm.containsKey(number)){
	        hm.put(number,2);
	    }else{
	        hm.put(number,1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find1(int value) {
	    Iterator<Integer> iter = hm.keySet().iterator();
	    while(iter.hasNext()){
	        int num1 = iter.next();
	        int num2 = value - num1;
	        if(hm.containsKey(num2)){
	            if(num1 != num2 || hm.get(num2) == 2){
	                return true;
	            }
	        }
	    }
	    return false;
	}
}
