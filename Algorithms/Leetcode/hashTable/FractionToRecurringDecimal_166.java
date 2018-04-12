package hashTable;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal_166 {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0)		// 分子为0，返回0
			return "0";
		if (denominator == 0)	// 分母为0，返回空
			return "";

		String result = "";

		// is result is negative
		if ((numerator < 0) ^ (denominator < 0)) {		// boolean logical exclusive OR
			result += "-";
		}

		// convert int to long
		long num = numerator, den = denominator;
		num = Math.abs(num);
		den = Math.abs(den);

		// quotient
		long res = num / den;
		result += String.valueOf(res);

		// if remainder is 0, return result
		long remainder = (num % den) * 10;
		if (remainder == 0)
			return result;

		// right-hand side of decimal point
		Map<Long, Integer> map = new HashMap<>();	// 余数reminder - 结果的长度 result.length()
		result += ".";
		while (remainder != 0) {
			
			// if digits repeat, 无限循环小数
			if (map.containsKey(remainder)) {
				int beg = map.get(remainder);
				String part1 = result.substring(0, beg);
				String part2 = result.substring(beg, result.length());
				result = part1 + "(" + part2 + ")";
				return result;
			}

			// continue
			map.put(remainder, result.length());
			res = remainder / den;
			result += String.valueOf(res);
			remainder = (remainder % den) * 10;
		}

		return result;
	}
}
