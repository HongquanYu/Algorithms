package dp;

public class RangeSumQuery1_303 {
	
	private int[] num;
	
    public RangeSumQuery1_303(int[] nums) {
    	
    	num = nums;
    	
        for (int i = 1; i < num.length; ++i)
        	num[i] += num[i-1];
    }
    
    public int sumRange(int i, int j) {
    	
    	if (i > 0)
    		return num[j] - num[i - 1];
    	
    	return num[j];
    }
}
