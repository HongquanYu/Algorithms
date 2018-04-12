package hashTable;

import java.util.HashSet;

public class DistributeCandies_575 {
	public int distributeCandies(int[] candies) {
		
		HashSet<Integer> set = new HashSet<>();
		for (int candy : candies) 
			set.add(candy);
		
		return Math.min(set.size(), candies.length / 2);
	}
}
