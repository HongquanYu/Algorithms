package string;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RomanToInteger_13 {
	public int romanToInt(String s) {
		int sum = 0;
		if (s.indexOf("IV") != -1) {
			sum -= 2;
		}
		if (s.indexOf("IX") != -1) {
			sum -= 2;
		}
		if (s.indexOf("XL") != -1) {
			sum -= 20;
		}
		if (s.indexOf("XC") != -1) {
			sum -= 20;
		}
		if (s.indexOf("CD") != -1) {
			sum -= 200;
		}
		if (s.indexOf("CM") != -1) {
			sum -= 200;
		}

		char c[] = s.toCharArray();
		int count = 0;

		for (; count <= s.length() - 1; count++) {
			if (c[count] == 'M')
				sum += 1000;
			if (c[count] == 'D')
				sum += 500;
			if (c[count] == 'C')
				sum += 100;
			if (c[count] == 'L')
				sum += 50;
			if (c[count] == 'X')
				sum += 10;
			if (c[count] == 'V')
				sum += 5;
			if (c[count] == 'I')
				sum += 1;

		}

		return sum;

	}
	
	public int romanToInt2(String s) {
		int nums[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case 'M':
					nums[i] = 1000;
					break;
				case 'D':
					nums[i] = 500;
					break;
				case 'C':
					nums[i] = 100;
					break;
				case 'L':
					nums[i] = 50;
					break;
				case 'X':
					nums[i] = 10;
					break;
				case 'V':
					nums[i] = 5;
					break;
				case 'I':
					nums[i] = 1;
					break;
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
