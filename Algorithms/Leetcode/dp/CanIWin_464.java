package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CanIWin_464 {
	private Map<Integer, Boolean> map;	// which combination of choosable sequence will make win
	private boolean[] used;				// current chose and un-chose number

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal)	// total is out of reach
			return false;
		if (desiredTotal <= 0)			// any bid the first player wins
			return true;

		map = new HashMap<>();
		used = new boolean[maxChoosableInteger + 1];
		return canWin(desiredTotal);
	}

	public boolean canWin(int desiredTotal) {	// check if there's a way to win the game!
		if (desiredTotal <= 0)
			return false;
		
		int key = format(used);
		if (!map.containsKey(key)) {					// try every unchosen number as next step
			for (int i = 1; i < used.length; i++) {
				if (!used[i]) {
					used[i] = true;					// check whether this lead to a win (i.e. the other player lose)
					if (!canWin(desiredTotal - i)) {	// choosing i will lead a win
						map.put(key, true);
						used[i] = false;
						return true;
					}
					used[i] = false;					// trackback to not use this i
				}
			}
			map.put(key, false);						// this combination cannot make a win
		}
		
		return map.get(key);
	}
	
	public int format(boolean[] used) {		// Mapping boolean[] to an Integer
		int num = 0;
		for (boolean b : used) {
			num <<= 1;
			if (b)
				num |= 1;
		}
		return num;
	}
	
	/* Quickest submission of LeetCode */
	
	public class Solution {
		public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
			int total = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
			if (total < desiredTotal)
				return false;
			if (maxChoosableInteger == 10 && desiredTotal == 40)
				return false;
			if (maxChoosableInteger == 7 && desiredTotal == 16)
				return true;
			if (maxChoosableInteger == 10 && desiredTotal == 54)
				return false;
			if (maxChoosableInteger == 5 && desiredTotal == 12)
				return true;
			if (maxChoosableInteger == 20 && desiredTotal == 209)
				return false;
			if (maxChoosableInteger >= desiredTotal)
				return true;
			if (desiredTotal % (maxChoosableInteger + 1) == 0)
				return false;
			return true;
		}
	}
	
    private int[] dp;
    private boolean[] usedNumber;
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(sum < desiredTotal) {        //取完所有数，也达不到desiredTotal，无法赢得游戏
            return false;
        }
        if(desiredTotal <= maxChoosableInteger) {      //第一步就可以获得胜利
            return true;
        }
        dp = new int[1 << maxChoosableInteger];			// 2^maxChoosableInteger numbers to dp array
        Arrays.fill(dp , -1);
        usedNumber = new boolean[maxChoosableInteger + 1];
        
        return helper(desiredTotal);
    }
    
    public boolean helper(int desiredTotal){
        if(desiredTotal <= 0) {
            return false;
        }
        int key = format(usedNumber);         		//把used数组转为十进制表示
        if(dp[key] == -1){ 
            for(int i = 1; i < used.length; i++){    //枚举未选择的数
                if(!used[i]){
                    used[i] = true;

                    if(!helper(desiredTotal - i)){
                        dp[key] = 1;
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            dp[key] = 0;
        }
        return dp[key] == 1;
    }
}
