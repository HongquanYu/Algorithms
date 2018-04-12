package hashTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall_554 {
	
	/** Optimal */
	
	public int leastBricks(List<List<Integer>> wall) {
		if (wall == null || wall.size() == 0)
			return 0;
		int count = 0;
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (List<Integer> list : wall) {
			int length = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				length += list.get(i);
				map.put(length, map.getOrDefault(length, 0) + 1);
				count = Math.max(count, map.get(length));
			}
		}
		
		return wall.size() - count;
	}
	
	public int leastBricks2(List<List<Integer>> wall) {
		
		HashMap<Integer, Integer> map = new HashMap<>();		// 行的cumulative sum的个数
		
		// 将所有行的 每一块砖的累加和 和 他出现的次数 存在map里
		for (List<Integer> row : wall) {
			int sum = 0;
			for (int i = 0; i < row.size() - 1; i++) {
				sum += row.get(i);
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}
		
		int res = wall.size();
		for (int key : map.keySet())
			res = Math.min(res, wall.size() - map.get(key));
		
		return res;
	}
}
