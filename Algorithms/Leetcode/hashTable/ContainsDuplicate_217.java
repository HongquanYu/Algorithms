package hashTable;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate_217 {
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>(nums.length);
		
        for (int i : nums)
            if(!set.add(i))
                return true;
        
		return false;
	}
}
