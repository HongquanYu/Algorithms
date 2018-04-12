package array;

public class CanPlaceFlowers_605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int len = flowerbed.length;
		int validPlots = 0;								// number of valid plots
		int[] flower = new int[flowerbed.length + 2];	// to avoid deal with corner cases separately
		
		for (int i = 1; i <= len; ++i) {
			flower[i] = flowerbed[i - 1];
		}
    
    for (int i = 1; i <= len; ++i) {
    		if (flower[i] == 1)
    			i++;
    		else if ((flower[i] ^ flower[i - 1]) == 0 && (flower[i] ^ flower[i + 1]) == 0){
    			validPlots++;
                i++;
    		}
    	
    		if (validPlots >= n)				// enough spots for n
    			return true;
    }
    
    return false;
    }
    
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }
    
    /* The intuition is to find the element is 0 which it's previous and next element are both 0s. Mark it with 1 and 
     * increase the counter to a valid spot! Of course there are boundary cases! */
    
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
             if(count>=n)
                return true;
            i++;
        }
        return false;
    }
}
