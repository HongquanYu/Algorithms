package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindKClosestElements_658 {

	/** 新接口题 */
	
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int N = arr.length;
        
        if (x <= arr[0])				return copyArrayRange(arr, 0, k - 1);
        else if (arr[N - 1] <= x)	return copyArrayRange(arr, N - k, N - 1);
        else {
        		int start = Arrays.binarySearch(arr, x);
        		if (start < 0)	start = -start - 1;
        		
        		// 线性查找 [start - k, start + k] 区间，直到最后只剩 k 个元素为止
        		int lo = Math.max(0, start - k - 1);
        		int hi = Math.min(N - 1, start + k - 1);
        		
        		while (hi - lo > k - 1) {
        			if (lo < 0 || x - arr[lo] <= arr[hi] - x)	hi--;	// 哪边离得远移动哪边
        			else											lo++;
        		}
        		return copyArrayRange(arr, lo, hi);
        }
    }
    private List<Integer> copyArrayRange(int[] nums, int lo, int hi) {
    		List<Integer> res = new ArrayList<>();
    		for (int i = lo; i <= hi; ++i)
    			res.add(nums[i]);
    		return res;
    }
    
	/** Old version of question */
    
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		int n = arr.size();
		if (x <= arr.get(0)) {
			return arr.subList(0, k);
		} else if (arr.get(n - 1) <= x) {
			return arr.subList(n - k, n);
		} else {
			int index = Collections.binarySearch(arr, x);
			if (index < 0)
				index = -index - 1;
			int low = Math.max(0, index - k - 1), high = Math.min(arr.size() - 1, index + k - 1);

			while (high - low > k - 1) {
				if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x))
					high--;
				else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x))
					low++;
				else
					System.out.println("unhandled case: " + low + " " + high);
			}
			
			return arr.subList(low, high + 1);
		}
	}
}
