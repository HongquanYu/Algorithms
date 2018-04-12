package reservoirSampling;

/** @author: Hongquan Yu
 *  @date: Mar 14, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class GetSubarrayWithEqualProbability {
	
	/** CTCI Chapter 20.3 */
	
	public static int rand(int lower, int higher) {
		return lower + (int)(Math.random() * (higher - lower + 1));
	}
	
	public static int[] pickRandomly(int[] original, int m) {
		int[] subset = new int[m];
		int[] array = original.clone();
		
		for (int j = 0; j < m; ++j) {
			int idx = rand(j, array.length - 1);
			subset[j] = array[idx];
			array[idx] = array[j];
		}
		
		return subset;
	}
}
