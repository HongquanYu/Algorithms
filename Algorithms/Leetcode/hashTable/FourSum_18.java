package hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FourSum_18 {
	
	/* N3 SOLUTION */
	
	public List<List<Integer>> fourSumOne(int[] nums, int target) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();

		int n = nums.length;

		Arrays.sort(nums);

		/* Now fix the first 2 elements one by one and find the other two elements */
		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {		// skip duplicates
					int l = j + 1, r = n - 1;
					
					while (l < r) {
						if (nums[i] + nums[j] + nums[l] + nums[r] == target) {
							List<Integer> sub = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
							if (!res.contains(sub)) 	res.add(sub);	// 有相等的值在结果里
							l++; r--;	// 同时移动
						} else if (nums[i] + nums[j] + nums[l] + nums[r] < target)
							l++;		// 最低指针移动
						else
							r--;		// 最高指针移动
					}
				}
			}
		}

		return res;
    }
	
	/* N2 SOLUTION */
	
	public List<List<Integer>> fourSumTwo(int[] nums, int target) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();	// 存的是两个元素的和 和 数组元素

		int n = nums.length;
		
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				
				int sum = nums[i] + nums[j];
				
				if (map.containsKey(target - sum)) {		// 能找到一个 valid 组合
					for (int[] pair : map.get(target - sum)) {
						if (noDuplicate(i, j , pair)) {		// 四个quadruplet里面不会有重复
							List<Integer> sub = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[pair[0]], nums[pair[1]]));
							Collections.sort(sub);
							if (!res.contains(sub)) res.add(sub);		
						}
					}
				}
				if (!map.containsKey(sum)) {			// 不含邮当前和，添加进去
					map.put(sum, new LinkedList<>());
				}
				int[] pair = new int[] {i, j};
				if (!map.get(sum).contains(pair))
					map.get(sum).add(pair);
			}
		}
		
		return res;
	}
	
	private boolean noDuplicate(int i, int j, int[] k) {
		return i != k[0] && i != k[1] && j != k[0] && j != k[1];
	}
	
    public static void main(String[] args) {
		int[] num = {1, 0, -1, 0, -2, 2};
		int target = 0;
		
		List<List<Integer>> t = fourSum3(num, target);
		
		System.out.print("[ ");
		for (int i = 0; i < t.size(); i++) {
			System.out.print("[ ");
			
			for (int p : t.get(i)) {
				System.out.print(p +", ");
			}
			
			System.out.println(" ], ");
		}
		System.out.println(" ]");
    }
	
	
	
	
	/* BTF n4 four loops*/
	
	/* based on 2sum and 3sum, n3 */
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (nums == null || len < 4)
			return res;

		Arrays.sort(nums);

		int max = nums[len - 1];
		if (4 * nums[0] > target || 4 * max < target)		// boundary check
			return res;

		int i, z;
		for (i = 0; i < len; i++) {
			z = nums[i];
			if (i > 0 && z == nums[i - 1])			// avoid duplicate
				continue;
			if (z + 3 * max < target) 				// z is too small
				continue;
			if (4 * z > target) 						// z is too large
				break;
			if (4 * z == target) { 					// z is the boundary
				if (i + 3 < len && nums[i + 3] == z)
					res.add(Arrays.asList(z, z, z, z));
				break;
			}
			threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);		// reduce to three sum
		}

		return res;
	}

	/*
	 * Find all possible distinguished three numbers adding up to the target in sorted array nums[]
	 * between indices low and high. If there are, add all of them into the ArrayList fourSumList,
	 * using fourSumList.add(Arrays.asList(z1, the three numbers))
	 */
	public static void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList, int z1) {
		if (low + 1 >= high)
			return;

		int max = nums[high];
		if (3 * nums[low] > target || 3 * max < target)
			return;

		int i, z;
		for (i = low; i < high - 1; i++) {
			z = nums[i];
			if (i > low && z == nums[i - 1]) 			// avoid duplicate
				continue;
			if (z + 2 * max < target) 					// z is too small
				continue;
			if (3 * z > target) 							// z is too large
				break;
			if (3 * z == target) { 						// z is the boundary
				if (i + 1 < high && nums[i + 2] == z)
					fourSumList.add(Arrays.asList(z1, z, z, z));
				break;
			}
			twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
		}
	}

	/*
	 * Find all possible distinguished two numbers adding up to the target in sorted array nums[]
	 * between indices low and high. If there are, add all of them into the ArrayList fourSumList,
	 * using fourSumList.add(Arrays.asList(z1, z2, the two numbers))
	 */
	public static void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList, int z1, int z2) {
		if (low >= high)
			return;

		if (2 * nums[low] > target || 2 * nums[high] < target)
			return;

		int i = low, j = high, sum, x;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

				x = nums[i];
				while (++i < j && x == nums[i]) ;	// avoid duplicate
				x = nums[j];
				while (i < --j && x == nums[j]) ;	// avoid duplicate
			}
			if (sum < target)
				i++;
			if (sum > target)
				j--;
		}
		return;
	}
	
	/* base on three sum */
	
	public List<List<Integer>> fourSum2(int[] num, int target) {
		ArrayList<List<Integer>> ans = new ArrayList<>();
		if (num.length < 4)
			return ans;
		Arrays.sort(num);
		for (int i = 0; i < num.length - 3; i++) {		// fix the first element
			if (num[i] + num[i + 1] + num[i + 2] + num[i + 3] > target)	// first candidate too large, search finished
				break; 
			if (num[i] + num[num.length - 1] + num[num.length - 2] + num[num.length - 3] < target)	// first candidate too small
				continue; 
			if (i > 0 && num[i] == num[i - 1])	// prevents duplicate result in ans list
				continue; 
			for (int j = i + 1; j < num.length - 2; j++) {
				if (num[i] + num[j] + num[j + 1] + num[j + 2] > target)	// second candidate too large
					break; 
				if (num[i] + num[j] + num[num.length - 1] + num[num.length - 2] < target)	// second candidate too small
					continue; 
				if (j > i + 1 && num[j] == num[j - 1])	// prevents duplicate results in ans list
					continue; 
				int low = j + 1, high = num.length - 1;
				while (low < high) {
					int sum = num[i] + num[j] + num[low] + num[high];
					if (sum == target) {
						ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
						while (low < high && num[low] == num[low + 1])	// skipping over duplicate on low
							low++; 
						while (low < high && num[high] == num[high - 1])	 // skipping over duplicate on high
							high--;
						low++;
						high--;
					}
					// move window
					else if (sum < target)
						low++;
					else
						high--;
				}
			}
		}
		return ans;
	}
	
	/* n2-logn n2 space, the solution set contains a lot of duplicates.
	 * But it's a great solution that can be useful */
	
    public static List<List<Integer>> fourSum3(int[] nums, int target) {
    		ArrayList<List<Integer>> ans = new ArrayList<>();
    		if (nums.length < 4)
    			return ans;
    		
    		Pair[] aux = new Pair[nums.length * (nums.length - 1) / 2];
    		int k = 0;
    		for (int i = 0; i < nums.length - 1; ++i)
    			for (int j = i + 1; j < nums.length; ++j)
    				aux[k++] = new Pair(i, j, nums[i] + nums[j]);

    		Arrays.sort(aux, (a, b) -> a.sum - b.sum);
    		
    		int i = 0, j = aux.length - 1;
    		while (i < aux.length && j >= 0) {
    			if (aux[i].sum + aux[j].sum == target && !shareSumElement(aux[i], aux[j])) {
    				ans.add(Arrays.asList(nums[aux[i].idx1], nums[aux[i].idx2], nums[aux[j].idx1], nums[aux[j].idx2]));
    				j--;
    			}
    			else if (aux[i].sum + aux[j].sum < target)
    				i++;
    			else
    				j--;
    		}
    		
    		return ans;
    }
    
    private static boolean shareSumElement(Pair a, Pair b) {
    		return (a.idx1 == b.idx1) || (a.idx1 == b.idx2) || (a.idx2 == b.idx1) || (a.idx2 == b.idx2);
    }
    
	static class Pair {
		int idx1, idx2, sum;
		public Pair(int f, int s, int sum) {
			this.idx1 = f;
			this.idx2 = s;
			this.sum = sum;
		}
	}
}
