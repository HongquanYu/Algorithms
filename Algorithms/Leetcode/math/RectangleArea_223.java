package math;

/** @author: Hongquan Yu
 *  @date: Mar 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RectangleArea_223 {
	
	/** 问题是，找出两个矩形覆盖的面积！
	 *  */
	
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		
		int areaA = (C - A) * (D - B);
		int areaB = (G - E) * (H - F);
		
		int left = Math.max(A, E);
		int right = Math.max(Math.min(C, G), left);
		int bottom = Math.max(B, F);
		int top = Math.max(Math.min(D, H), bottom);
		
		return areaA - (right - left) * (top - bottom) + areaB;
	}
	
	/** 最直观的解！分三步：
	 * 1， 计算两个矩形的面积
	 * 2， 计算重合部分的面积
	 * 3， 两矩形面积之和减去重合面积 */
	
	public int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {

		int areaA = (C - A) * (D - B);
		int areaB = (G - E) * (H - F);

		int x1 = Math.max(A, E);
		int x2 = Math.min(G, C);
		int y1 = Math.max(F, B);
		int y2 = Math.min(D, H);

		// If overlap
		int overlap = 0;
		if (x2 > x1 && y2 > y1)
			overlap = (x2 - x1) * (y2 - y1);

		return areaA + areaB - overlap;
	}
}
