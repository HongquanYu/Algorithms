package binarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class IntersectionOfTwoArrays_349 {
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Arrays.sort(nums2);

		for (Integer num : nums1)
			if (Arrays.binarySearch(nums2, num) > 0)
				set.add(num);

		int i = 0;
		int[] result = new int[set.size()];
		for (Integer num : set)
			result[i++] = num;
		
		return result;
	}
	
	/* Self defined binary search function, instead of using library defined methods */
	
    public int[] intersection1(int[] nums1, int[] nums2) {
        
        Set<Integer> set = new HashSet <>();
        Arrays.sort(nums1);
        
        for ( int num : nums2){
            if (binarySearch(nums1, num)){
                set.add(num);
            }
        }
        
        int[] result = new int[set.size()];
        int i = 0;
        
        for ( int n : set){
            result[i++] = n;
        }
        return result;
    }
    
    private boolean binarySearch (int[] nums , int target){
        
        if (nums == null || nums.length == 0) return false;
        
        int start = 0, end = nums.length -1;
        
        while ( start <= end){
            int mid = start + (end - start) / 2;
            
            if (target > nums[mid]) start = mid + 1;
            else if (target < nums[mid]) end = mid - 1;
            else return true;
        }
        
        return false;
    }
	
	
	/* Linear solution. Used java 8 new stream features! */
	
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>(), res = new HashSet<>();
        
        for (int x : nums1)
            set.add(x);
        
        for (int x : nums2) {
            if (set.contains(x))
                res.add(x);
        }
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
