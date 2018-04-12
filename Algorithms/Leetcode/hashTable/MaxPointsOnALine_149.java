package hashTable;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine_149 {
	
	/** 遍历每个点，看它和后面的每个点构成的直线上有多少个点 对每个点建立map，
	 * 斜率是key 斜率要用分数的形式， 不要用double的形式存。计算分数时先求分子
	 * 分母的最大公约数gcd，再都除以gcd。重合的点特殊处理 */
	
	public int maxPoints(Point[] points) {
		int N = points.length;
		if (N <= 2) 	return N;

		int res = 0;
		for (int i = 0; i < N - 1; i++) {
			Map<String, Integer> map = new HashMap<>();
			int overlaps = 0, inlines = 0;	// 找出所有的重合点和在一条直线上的点
			
			for (int j = i + 1; j < N; j++) {
				int horizontalDiff = points[i].x - points[j].x; 		// 两个点的x坐标差
				int verticalDiff   = points[i].y - points[j].y; 		// 两个点的y坐标差
				
				if (horizontalDiff == 0 && verticalDiff == 0) {		// 横纵坐标都相同，重合
					overlaps++;
					continue;
				}
				int gcd = generateGCD(horizontalDiff, verticalDiff);
				horizontalDiff /= gcd;
				verticalDiff /= gcd;
				
				String slope = String.valueOf(horizontalDiff) + String.valueOf(verticalDiff);		// 用string来存储斜率
				int count = map.getOrDefault(slope, 0) + 1;
				map.put(slope, count);
				inlines = Math.max(inlines, count);
			}
			res = Math.max(res, inlines + overlaps + 1);
		}
		return res;
	}

	public int generateGCD(int x, int y) {
		if (y == 0) 	return x;
		return generateGCD(y, x % y);
	}
}
