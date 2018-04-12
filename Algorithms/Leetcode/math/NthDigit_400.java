package math;

/** @author: Hongquan Yu
 *  @date: Mar 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class NthDigit_400 {
	
	/** 自然数的规律，
	 * 1 digit: 		1 ~ 9
	 * 2 digits:		10 ~ 99
	 * 3 digits:		100 ~ 999
	 * ......
	 * 我们定义一个 rangeCount 来记录range有多少数，每次循环扩大 10 倍，再用一个变量 rangeNumberLength 来计数当前区间有多少个数，
	 * 另外我们需要一个 rangeStart 来记录当前 range 的起始数字。每次参数 n 都循环减去 rangeNumberLength * rangeCount,
	 * 当确定 n 落到某一个区间里了，(n-1) / rangeNumberLength 就是在该区间的 位置。再加上 rangeStart 就得到了目标数字
	 * 取 (n-1) % rangeNumberLength 就是所要求的目标位
	 * 
	 * 解决此问题要有 3 步：
	 * 1，找到包含 nth digit 的数的长度
	 * 2，找到那个数
	 * 3，找到第 n 个 digit */
	
	public int findNthDigit(int n) {
		int rangeNumberLength = 1;	// 每个 range 里的数有几位
		long rangeCount = 9;			// 每个 range 里有多少位数
		int rangeStart = 1;			// 当前 range 的第一个数是什么

		while (n > rangeNumberLength * rangeCount) {
			n -= rangeNumberLength * rangeCount;
			rangeNumberLength += 1;
			rangeCount *= 10;
			rangeStart *= 10;
		}

		rangeStart += (n - 1) / rangeNumberLength;	// 找到区间内的该数
		String s = Integer.toString(rangeStart);		// 将数转化成字符串，方便取出某一位
		
		return Character.getNumericValue(s.charAt((n - 1) % rangeNumberLength));
	}
}
