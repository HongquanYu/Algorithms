package bfs;

public class ShortestDistanceFromAllBuilding_317 {
    public int shortestDistance(int[][] grid) {
    		int minDistance = Integer.MAX_VALUE;
        int rangeXmin = 0, rangeXmax = 0;
        int rangeYmin = 0, rangeYmax = 0;
        int row = grid.length, col = grid[0].length;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MAX_VALUE, maxY = Integer.MAX_VALUE;
        
        for (int i = 0; i < row; ++i) {
        		for (int j = 0; j < col; ++j) {
        			if (grid[i][j] == 1) {
        				minX = Math.min(minX, i);
        				minY = Math.min(minY, j);
        				maxX = Math.max(maxX, i);
        				maxY = Math.max(maxY, j);
        			}
        		}
        }
        
        rangeXmin = (maxX - minX) / 2 - 1;
        rangeXmax = (maxX - minX) / 2 + 1;
        rangeYmin = (maxY - minY) / 2 - 1;
        rangeYmax = (maxY - minY) / 2 + 1;
        
        for (int i = rangeXmin; i <= rangeXmax; ++i) {
        		for (int j = rangeYmin; j <= rangeYmax; ++j) {
        			
        		}
        }
    	
    		return minDistance;
    }
    
    private int distance(int x1,, int y1, int x2, int y2) {
    		return 0;
    }
}
