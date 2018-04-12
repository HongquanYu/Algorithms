package hashTable;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray_525 {
	
	/* 题意是要找到subarray中间长度最长的那个，使得其里的0和1的个数相等 */
	
	public int findMaxLength(int[] nums) {
		
		Map<Integer, Integer> sumToIndex = new HashMap<>();	//  accumulative sum和目前的index
		sumToIndex.put(0, -1);
		int maxlen = 0, count = 0;
		
		for (int i = 0; i < nums.length; i++) {
			
			count = count + (nums[i] == 1 ? 1 : -1);		// 用来计数 1 和 0 的个数
			
			if (sumToIndex.containsKey(count))		// 表明数组之前是有sum和现在sum一样的，也就是说从那个sum到现在这个sum之间的元素和为0
				maxlen = Math.max(maxlen, i - sumToIndex.get(count));
			else
				sumToIndex.put(count, i);
		}
		
		return maxlen;
	}
	
	public int findMaxLength1(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int zero = 0, one = 0, len = 0;
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) 	zero++;
			else 				one++;

			if (map.containsKey(zero - one))
				len = Math.max(len, i - map.get(zero - one));
			else
				map.put(zero - one, i);
		}

		return len;
	}
}
