package math;

/** @author: Hongquan Yu
 *  @date: Apr 6, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class PoorPigs_458 {
	
	/** 一个猪决定行，一个猪决定列，一个猪决定高。。。 */
	
	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
		int status = minutesToTest / minutesToDie + 1;
		int pigs = 0;
		
		while (Math.pow(status, pigs) < buckets)
			pigs++;
		
		return pigs;
	}
	
	public static void main(String[] args) {
		System.out.println(new PoorPigs_458().poorPigs(1000, 15, 60));
	}
}
