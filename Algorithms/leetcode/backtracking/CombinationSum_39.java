package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinationSum_39 {
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList(), candidates, target, 0);
		return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tmpList, int[] can, int tgt, int sum) {
		if (sum > tgt)
			return;
		else if (sum == tgt && !list.contains(tmpList))
			list.add(new ArrayList<>(tmpList));
		else {
			for (int i = 0; i < can.length; ++i) {
				System.out.println("Current item: " + can[i]);
				tmpList.add(can[i]);
				int tmpSum = sum + can[i];
				int[] tt = new int[can.length - 1];
				System.out.println();
				for (int j = 0, p = 0; p < can.length; ++p) {
					if (p == i)
						continue;
					tt[j++] = can[p];
					System.out.print(can[p] + ", ");
				}
				System.out.println();
				Collections.sort(tmpList);
				backtrack(list, tmpList, tt, tgt, tmpSum);
				tmpList.remove(tmpList.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] test = { 10, 1, 2, 7, 6, 1, 5 };

		System.out.println(combinationSum2(test, 8));
	}
}
