package noncomparison;

/** @author: Hongquan Yu
 *  @date: Feb 4, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: K, Time: N+K, Worst: N+K. K is the number range! */

public class CountingSort {
    public void sort(int[] nums) {
    	
		// Find minimum and maximum values
		int min = nums[0], max = nums[0];
		for (int num : nums) {
			if (num < min) 			min = num;
			else if (num > max) 		max = num;
		}

        int[] count = new int[max - min + 1];		// 分配好range，元素的值当作index，value是次数

        for (int n : nums)			// frequency to bucket index
            count[n - min]++;

        int idx = 0;
        for (int i = 0; i < count.length; i++) {		
            while (count[i] > 0) {
                nums[idx++] = i + min;
                count[i]--;
            }
        }
    }
    
	static void print(int arr[]) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + ", ");
	}

	public static void main(String[] args) {
		CountingSort cs = new CountingSort();
		int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
		cs.sort(arr);
		print(arr);
	}
}
