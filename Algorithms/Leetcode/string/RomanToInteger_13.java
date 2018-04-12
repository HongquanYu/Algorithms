package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RomanToInteger_13 {
	
	/** Count every Symbol and add its value to the sum, and minus the extra part of special cases. */
	
	public int romanToInt(String s) {
		int sum = 0;
		
		/* Special cases, should be deducted
		 * 数组： 'M' - 1000, 'D' - 500, 'C' - 100, 'L' - 50, 'X' - 10, 'V' - 5, 'I' - 1.
		 * 规则是: 
		 * 	- 大数字右边有小数字 表 大数字 + 小数字
		 *  - 大数字左边有小数字 表 大数字 - 小数字 */
		
		if (s.indexOf("IV") != -1) 	sum -= 2;	
		if (s.indexOf("IX") != -1) 	sum -= 2;
		if (s.indexOf("XL") != -1)	sum -= 20;
		if (s.indexOf("XC") != -1)	sum -= 20;
		if (s.indexOf("CD") != -1)	sum -= 200;
		if (s.indexOf("CM") != -1)	sum -= 200;

		// 遍历所有字符，他代表什么就加上相应数值
		for (char c : s.toCharArray()) {
			if (c == 'M') 	sum += 1000;
			if (c == 'D') 	sum += 500;
			if (c == 'C') 	sum += 100;
			if (c == 'L') 	sum += 50;
			if (c == 'X') 	sum += 10;
			if (c == 'V') 	sum += 5;
			if (c == 'I') 	sum += 1;
		}

		return sum;
	}
	
	public int romanToInt2(String s) {
		
		int nums[] = new int[s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case 'M': { nums[i] = 1000; 	break; }
				case 'D': { nums[i] = 500; 	break; }
				case 'C': { nums[i] = 100; 	break; }
				case 'L': { nums[i] = 50; 	break; }
				case 'X': { nums[i] = 10; 	break; }
				case 'V': { nums[i] = 5; 	break; }
				case 'I': { nums[i] = 1; 	break; }
			}
		}
		
		int sum = 0;
		
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] < nums[i + 1])
				sum -= nums[i];
			else
				sum += nums[i];
		}
		
		return sum + nums[nums.length - 1];
	}
}
