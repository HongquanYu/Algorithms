package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers_638 {
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special,
			List<Integer> needs) {
		Map<List<Integer>, Integer> dp = new HashMap<>();
		List<Integer> allZero = new ArrayList<>();
		for (int i = 0; i < needs.size(); i++) {
			allZero.add(0);
		}
		dp.put(allZero, 0);
		return dfs(needs, price, special, dp);
	}

	private int dfs(List<Integer> needs, List<Integer> price, List<List<Integer>> special,
			Map<List<Integer>, Integer> dp) {
		if (dp.containsKey(needs))
			return dp.get(needs);
		int res = Integer.MAX_VALUE;
		for (List<Integer> s : special) {
			List<Integer> needsCopy = new ArrayList<>(needs);
			boolean valid = true;
			for (int i = 0; i < needs.size(); i++) {
				needsCopy.set(i, needsCopy.get(i) - s.get(i));
				if (needsCopy.get(i) < 0) {
					valid = false;
					break;
				}
			}
			if (valid) {
				res = Math.min(res, s.get(needs.size()) + dfs(needsCopy, price, special, dp));
			}
		}
		// What if we do not use specials? specials can be deceiving,
		// perhaps buying using regular prices is cheaper.
		int noSpecial = 0;
		for (int i = 0; i < needs.size(); i++) {
			noSpecial += needs.get(i) * price.get(i);
		}
		res = Math.min(res, noSpecial);

		dp.put(needs, res);
		return res;
	}
}
