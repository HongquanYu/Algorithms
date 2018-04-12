package binarySearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindKCosestElements_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>();
        int n = arr.length;
        
        if (x < arr[0])
            rangeCopy(arr, res, 0, k - 1);
        else if (x > arr[n - 1])
            rangeCopy(arr, res, n - k , n - 1);
        else {
            int idx = Arrays.binarySearch(arr, x);
            if (idx < 0)
                idx = -(1 + idx);
            
            int left = Math.max(0, idx - k - 1);			// 
            int right = Math.min(n - 1, idx + k - 1);
            
            while (right - left > k - 1) {
                if (arr[right] - x >= x - arr[left])
                    right--;
                else
                    left++;
            }
            
            rangeCopy(arr, res, left, right);
        }

        return res;
    }
    
    private void rangeCopy(int[] nums, List<Integer> list, int lo, int hi) {
        for (int i = lo; i <= hi; ++i) 
            list.add(nums[i]);
    }
    
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int lo = 0, hi = arr.length - k;
        
        /* To get the start position of the final range! Assume the range is in even divided in both sides of 
         * Array, move the bigger difference side closer to */
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            
            if (x - arr[mid] > arr[mid + k] - x)		// 
                lo = mid + 1;
            else
                hi = mid;
        }
        
        List<Integer> res = new LinkedList<>();
        for (int i = lo; i < lo + k; ++i)
        		res.add(arr[i]);
        
        return res;
    }
}
