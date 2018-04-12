package binarySearch;

public class SearchInRotatedArray_303 {
    public static int search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {   // Only two situations
			int mid = (lo + hi) / 2;
			if (nums[mid] > nums[hi])   // mid > hi
				lo = mid + 1;
			else                        // mid < hi
				hi = mid;
		}
		System.out.println("smallest: " + nums[lo]);
		
		int rot = lo;
		lo = 0;
		hi = nums.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			int realmid = (mid + rot) % nums.length;
			if (nums[realmid] == target)
				return realmid;
			if (nums[realmid] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
    }
	
	public static void main(String[] args) {
		int[] a = {233, 333, 433, 633, 2, 12, 22, 32, 42, 52, 62, 72, 82};
		System.out.println(search(a, 633));
	}
}
