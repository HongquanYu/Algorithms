package dp;

/** 198. House Robber
 * 
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police. */

public class HouseRobber1_198 {
	
	/**
	 * Let us denote that:
	 * 
	 * F(k) = largest amount that you can rob from the first k houses A(i) =
	 * Amount of money at the ith house.
	 * 
	 * case n = 1: clearly F(1) = A(1). 
	 * case n = 2: F(2) = max(A(1), A(2)) 
	 * 
	 * For n = 3: 1, Rob the third house, and add its amount to the first house's
	 * amount. 2, Do not rob the third house, and stick with the maximum amount
	 * of the first two houses.
	 * 
	 * Clearly, you want to choose the larger of the two options at each step.
	 * Therefore, we could summarize the formula as following:
	 * 
	 * F(k) = max( F(k-2) + A(k), F(k-1) )
	 * 
	 * recursive methods, top-down solution. time complexity: O(nlgn) space
	 * complexity: O(1)
	 * 
	 * We could use an array to store and calculate the result, but since at
	 * each step you only need the previous two maximum values, two variables
	 * are suffice. */
	
	public int rob(int[] num) {
		
		int prevMax = 0;
		int currMax = 0;
		
		for (int x : num) {
			int temp = currMax;
			currMax = Math.max(prevMax + x, currMax);
			prevMax = temp;
		}
		return currMax;
	}
}
