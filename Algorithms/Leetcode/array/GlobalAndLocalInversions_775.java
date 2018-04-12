package array;

/** @author: Hongquan Yu
 *  @date: Mar 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class GlobalAndLocalInversions_775 {
	
	/** All local inversions are global inversions. 
	 * If the number of global inversions is equal to the number of local inversions, 
	 * it means that all global inversions in permutations are local inversions. 
	 * It also means that we can not find A[i] > A[j] with i+2 <= j. 
	 * In other words, max(A[i]) < A[i+2]
	 * 
	 * In this first solution, I traverse A and keep the current biggest number cmax. 
	 * Then I check the condition cmax < A[i+2] */
	
	public boolean isIdealPermutation(int[] A) {
		int currMax = 0;
		
		for (int i = 0; i < A.length - 2; ++i) {
			currMax = Math.max(currMax, A[i]);
			
			if (currMax > A[i + 2])
				return false;
		}
		
		return true;
	}
}
