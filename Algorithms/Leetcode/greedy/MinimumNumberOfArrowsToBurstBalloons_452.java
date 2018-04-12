package greedy;

import java.util.Arrays;

/* What do we do of this question?
 * For every balloon, there must be an arrow within it's range, because we need to shot it down.
 * The question becomes how do we choose the arrow position so we can use the minimum arrows to shot all the balloons?
 * IF we sort the balloons using the first coordinate, then whether we choose the any number within range [start, end],
 * we cannot get an optimal solution.
 * Then we could try use the second coordinate, and put the arrow position in the end of first balloon, we can guarantee
 * that we could use minimum arrow to shot all balloons. */

public class MinimumNumberOfArrowsToBurstBalloons_452 {
	public int findMinArrowShots(int[][] points) {
		if (points.length == 0) {
			return 0;
		}
		Arrays.sort(points, (a, b) -> a[1] - b[1]);
		int arrowPos = points[0][1];					// Always at the end position of first balloon
		int arrowCnt = 1;
		
		for (int i = 1; i < points.length; i++) {
			if (arrowPos >= points[i][0]) {			// Check how many balloons can be shot under current arrow
				continue;
			}
			arrowCnt++;								// add one arrow
			arrowPos = points[i][1];					// Move 	arrow to next position									
		}
		
		return arrowCnt;
	}
	
	/* Another solution using first coordinate to sort the balloons
	 *  */
	
	public int findMinArrowShots1(int[][] points) {
		if (points == null || points.length < 1)
			return 0;
		Arrays.sort(points, (a, b) -> (a[0] - b[0]));
		int result = 1;
		int end = points[0][1];					// End position of first arrow
		for (int i = 1; i < points.length; i++) {
			if (points[i][0] > end) {		// if current balloon is in the arrow shoting range
				result++;
				end = points[i][1];
			} else {							// current balloon is out of the arrow shoting range
				end = Math.min(end, points[i][1]);	// Find end of FIRST balloon
			}
		}
		return result;
	}
}
