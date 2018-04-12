package math;

public class SquirrelSimulation_573 {
	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int dis = 0;
        int min = Integer.MAX_VALUE;
        int mark = -1;
        
        for (int i = 0; i < nuts.length; ++i) {		// find the closest nut from squirrel
        	int squirrelToNut = (Math.abs(nuts[i][1] - squirrel[1]) + Math.abs(nuts[i][0] - squirrel[0]));
        	int NutToTree = (Math.abs(nuts[i][1] - tree[1]) + Math.abs(nuts[i][0] - tree[0]));
        	if (squirrelToNut + NutToTree < min) {
        		min = squirrelToNut + NutToTree;
        		mark = i;
        	}
        }
        dis += Math.abs(nuts[mark][1] - squirrel[1]) + Math.abs(nuts[mark][0] - squirrel[0]) +
        		(Math.abs(nuts[mark][1] - tree[1]) + Math.abs(nuts[mark][0] - tree[0]));
        
        for (int i = 0; i < nuts.length; ++i) {	
        	if (i == mark) {
        		dis += (Math.abs(nuts[i][1] - tree[1]) + Math.abs(nuts[i][0] - tree[0]));
        	} else {
        		dis += ((Math.abs(nuts[i][1] - tree[1]) + Math.abs(nuts[i][0] - tree[0])) * 2);
        	}
        }
        
        return dis;
    }
}
