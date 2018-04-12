package dp;

/* Find consecutive arithmetic slices clusters, and add the clusters all(the biggest number in the array)
 * 
 */

public class ArithmeticSlices_413 {
    public int numberOfArithmeticSlices(int[] A) {
    	int len = A.length;
    	if (len < 3)
    		return 0;
    	
    	int res = 0;
    	int subSeq = 2;
    	
        for (int i = 2; i < len; ++i) {
        	if (A[i] - A[i - 1] == A[i - 1] - A[i - 2])
        		subSeq++;
        	else {
        		if (subSeq >= 3) {
        			res += (subSeq - 1) * (subSeq - 2) / 2;
        			subSeq = 2;
        		}
        	}
        }
        if (subSeq != 2)
        	res += (subSeq - 1) * (subSeq - 2) / 2;
        
    	return res;
    }
    
	public int numberOfArithmeticSlices_dp(int[] A) {
		int[] dp = new int[A.length];
		int sum = 0;
		
		for (int i = 2; i < dp.length; i++) {
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				dp[i] = 1 + dp[i - 1];
				sum += dp[i];
			}
		}
		return sum;
	}
}
