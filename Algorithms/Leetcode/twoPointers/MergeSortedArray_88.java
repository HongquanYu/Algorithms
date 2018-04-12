package twoPointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MergeSortedArray_88 {
	
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int ptr1 = m - 1, ptr2 = n - 1;
		int res = m + n - 1;

		while (ptr1 >= 0 && ptr2 >= 0) {
			if (nums1[ptr1] > nums2[ptr2])
				nums1[res--] = nums1[ptr1--];
			else
				nums1[res--] = nums2[ptr2--];
		}
		while (ptr2 >= 0)
			nums1[res--] = nums2[ptr2--];
	}
	
	
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int k = m + n - 1;
        
        while (k > i) {		// If k catches i, then all the elements are in position
            
        		if (i >= 0 && j >= 0 && nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
    }
}
