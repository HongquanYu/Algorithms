package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinationSum_39 {
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), candidates, target, 0);
		return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] can, int tgt, int sum) {
		if (sum > tgt)
			return;
		else if (sum == tgt && !list.contains(tmpList))
			list.add(new ArrayList<>(tmpList));
		else {
			for (int i = 0; i < can.length; ++i) {
				tmpList.add(can[i]);
				int tmpSum = sum + can[i];
				int[] tt = new int[can.length - 1];
				for (int j = 0, p = 0; p < can.length; ++p) {
					if (p == i)
						continue;
					tt[j++] = can[p];
				}
				Collections.sort(tmpList);
				backtrack(list, tmpList, tt, tgt, tmpSum);
				tmpList.remove(tmpList.size() - 1);
			}
		}
	}
	
	/** My concise solution. */
	
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        for (int cur : candidates) {
            if (target == cur) {
            		List<Integer> list = new ArrayList<>();
            		list.add(cur);
            		res.add(list);
            } else if (target > cur) {
            		for (List<Integer> sub : combinationSum(candidates, target - cur)) {
            			sub.add(cur);
            			sub.sort((a, b) -> a - b);
            			if (!res.contains(sub))
            				res.add(sub);
            		}
            }
        }
        
        return res;
    }

	public static void main(String[] args) {
		int[] test = { 10, 1, 2, 7, 6, 1, 5 };
		int[] test2 = { 2, 3, 6, 7 };

//		System.out.println(combinationSum(test, 8));
		System.out.println(combinationSum(test, 8));
	}
}
