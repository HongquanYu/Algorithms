package math;

import java.util.HashSet;

/** @author: Hongquan Yu
 *  @date: Mar 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PerfectRectangle_391 {
	
	/** 初步想法是计算每个小 rectangle 的面积，然后加起来看是否等于 merge 过后的面积
	 * 正确的答案必须要满足两个条件：
	 * 1，大矩形的面积得等于小矩形的和
	 * 2，大矩形的四个角只有一个点出现，其余的每个point出现的次数都应该是偶数次！ */
	
    public static boolean isRectangleCover(int[][] rectangles) {
        int totalArea = 0;
        
        int[] merged = new int[4];
        merged[0] = merged[1] = Integer.MAX_VALUE;
        merged[2] = merged[3] = Integer.MIN_VALUE;
        
        HashSet<String> set = new HashSet<>();
        
        for (int[] rect : rectangles) {
        		totalArea += (rect[2] - rect[0]) * (rect[3] - rect[1]);
        		
        		merged[0]= Math.min(rect[0], merged[0]);
        		merged[1]= Math.min(rect[1], merged[1]);
        		merged[2]= Math.max(rect[2], merged[2]);
        		merged[3]= Math.max(rect[3], merged[3]);
        		
        		String s1 = rect[0] + " " + rect[1];
        		String s2 = rect[0] + " " + rect[3];
        		String s3 = rect[2] + " " + rect[3];
        		String s4 = rect[2] + " " + rect[1];
        		
        		if (!set.add(s1))	set.remove(s1);
        		if (!set.add(s2))	set.remove(s2);
        		if (!set.add(s3))	set.remove(s3);
        		if (!set.add(s4))	set.remove(s4);
        }
        	if ( set.size() != 4 ||
    			!set.contains(merged[0] + " " + merged[1]) ||
    			!set.contains(merged[0] + " " + merged[3]) ||
    			!set.contains(merged[2] + " " + merged[3]) ||
    			!set.contains(merged[2] + " " + merged[1]) )
        		return false;
        	
        int mergedArea = (merged[2] - merged[0]) * (merged[3] - merged[1]);
        
        return mergedArea == totalArea;
    }
    
    public static void main(String[] args) {
		int[][] rect = new int[][] { {1, 1, 3, 3}, 
									{3, 1, 4, 2}, 
									{3, 2, 4, 4}, 
									{1, 3, 2, 4}, 
									{2, 3, 3, 4} };
									
		System.out.println(isRectangleCover(rect));
	}
}
