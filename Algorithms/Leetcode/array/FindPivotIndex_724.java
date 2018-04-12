package array;

/** @author: Hongquan Yu
 *  @date: Mar 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class FindPivotIndex_724 {
    public int pivotIndex(int[] nums) {
    		int N = nums.length;
    		int[] sum = new int[N];
    		
    		for (int i = 0; i < N; ++i)
    			sum[i] = nums[i] + (i == 0 ? 0 : sum[i - 1]);
    		
    		for (int i = 0; i < N; ++i) {
            int left = (i == 0) ? 0 : sum[i - 1];
            int right = sum[N - 1] - sum[i];
            if (left == right)
                return i;
    		}
    		return -1;
    }
    
    public static void main(String[] args) {
		int[] tmp = new int[] {1, 7, 3, 6, 5, 6};
		int[] tmp2 = new int[] {1, 2, 3};
		int[] tmp3 = new int[] {-1, -1, -1, 0, 1, 1};
		
		FindPivotIndex_724 f = new FindPivotIndex_724();
		
		System.out.println(f.pivotIndex(tmp));
		System.out.println(f.pivotIndex(tmp2));
		System.out.println(f.pivotIndex(tmp3));
	}
}
