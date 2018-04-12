package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindNumberPattern {
	
	/** 有这个规律1 -> 10, 0 -> 01，第一行规定好是10，第二行根据这个规律，就会是10 01，
	 * 第三行继续拓展，10 01 01 10…… 问你第n行的第k个数字是什么。用recursion 
	 * 
	 * 下面解法的时间复杂度是： O(N* seedLength ^ n)*/
	
	public char findKthOfNthRow(String seed, int n, int k) {
		if (n == 1) 
			return seed.charAt(k - 1);
		StringBuilder sb = new StringBuilder();
		
		for (char c : seed.toCharArray()) {
			if (c == '1') 	sb.append("10");
			else 			sb.append("01");
		}
		
		return findKthOfNthRow(sb.toString(), n - 1, k);
	}
	
	public static void main(String[] args) {
		FindNumberPattern p = new FindNumberPattern();
		System.out.println("Row 2: ");
		System.out.println(p.findKthOfNthRow("10", 2, 1));
		System.out.println(p.findKthOfNthRow("10", 2, 2));
		System.out.println(p.findKthOfNthRow("10", 2, 3));
		System.out.println(p.findKthOfNthRow("10", 2, 4));
		System.out.println("Row 3: ");
		System.out.println(p.findKthOfNthRow("10", 3, 1));
		System.out.println(p.findKthOfNthRow("10", 3, 2));
		System.out.println(p.findKthOfNthRow("10", 3, 3));
		System.out.println(p.findKthOfNthRow("10", 3, 4));
		System.out.println(p.findKthOfNthRow("10", 3, 5));
		System.out.println(p.findKthOfNthRow("10", 3, 6));
		System.out.println(p.findKthOfNthRow("10", 3, 7));
		System.out.println(p.findKthOfNthRow("10", 3, 8));
	}
}
