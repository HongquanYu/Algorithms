package dfs;

/** @author: Hongquan Yu
 *  @date: Apr 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class FloodFill_733 {
	
	/** 最基本的 DFS，这里不用 visited 数组是因为我们有一道检验条件比较颜色 */
	
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    		int oldColor = image[sr][sc];
    		if (oldColor != newColor)
    			fill(image, sr, sc, newColor, oldColor);
        
        return image;
    }
    private void fill(int[][] image, int r, int c, int newColor, int oldColor) {
        int N = image.length, M = image[0].length;
        if (r < 0 || r >= N || c < 0 || c >= M)
            return;
        
        if (image[r][c] == oldColor) {
            image[r][c] = newColor;
            fill(image, r + 1, c, newColor, oldColor);
            fill(image, r - 1, c, newColor, oldColor);
            fill(image, r, c + 1, newColor, oldColor);
            fill(image, r, c - 1, newColor, oldColor);
        }
    }
}
