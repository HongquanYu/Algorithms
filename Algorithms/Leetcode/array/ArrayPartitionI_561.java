package array;

import java.util.Arrays;

/* How can we solve this problem?
 * First, let's think in this way. All elements in the array are going to be compared and
 * select the smaller one within one pair. Let's see the smallest element in this array. It
 * must be in the final result because in any combination of pairs, this smallest element
 * should be selected. No problem with this statement. The problem is how do we choose the other
 * element in of the pair contains smallest element. We definitely want to choose one to make 
 * the rest of elements as bigger as possible to do another round of selection like first one!
 * So, we are going to choose the smallest one excludes current smallest one.
 * 
 * The stratage is greedy algorithm! We select the smallest two element as one pair and add the smaller
 * element into result. And then we do the same process regarding to rest element until the whole 
 * elements are parsed done!
 */


public class ArrayPartitionI_561 {
	public int arrayPairSum(int[] nums) {
		
		Arrays.sort(nums);
		int result = 0;
		
		for (int i = 0; i < nums.length; i += 2) {
			result += nums[i];
		}
		
		return result;
	}
}
