package math;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class FractionAdditionAndSubtraction_592 {
	
	/** Least Common Multiple Solution
	 * 现将每一个要计算的 operator 给用一个链表存起来，然后来一个数取一个符号进行运算
	 *  */
	
	public String fractionAddition(String expression) {
		List<Character> sign = new ArrayList<>();
		
		for (int i = 1; i < expression.length(); i++) {
			char ops = expression.charAt(i);
			if (ops == '+' || ops == '-')
				sign.add(ops);
		}
		List<Integer> num = new ArrayList<>();	// 分子
		List<Integer> den = new ArrayList<>();	// 分母
		for (String sub : expression.split("(\\\\+)|(-)")) {
			if (sub.length() > 0) {
				String[] fraction = sub.split("/");
				num.add(Integer.parseInt(fraction[0]));
				den.add(Integer.parseInt(fraction[1]));
			}
		}
		if (expression.charAt(0) == '-')
			num.set(0, -num.get(0));
		int lcm = 1;		// 找到所有分母的最小公倍数
		for (int x : den) 
			lcm = lcm(lcm, x);

		int curNum = num.get(0) * (lcm / den.get(0));	// 每一个分子都得乘上 lcm / 当前分母
		for (int i = 1; i < num.size(); i++) {
			if (sign.get(i - 1) == '+')
				curNum += num.get(i) * (lcm / den.get(i));
			else
				curNum -= num.get(i) * (lcm / den.get(i));
		}
		int g = gcd(Math.abs(curNum), Math.abs(lcm));	// 对答案的分子分母进行约分
		return (curNum / g) + "/" + (lcm / g);
	}
	
	/** 获取两个数的最小公倍数: 两者乘积除以最大公约数 */
	
	public int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
	
	/** Greatest Common Divisor Solution.
	 * 我们直到做算术运算是：要将分母通分，再将分子也相乘再相加！ */
	
	public String fractionAddition2(String expression) {
		List<Character> sign = new ArrayList<>();
		
		if (expression.charAt(0) != '-')
			sign.add('+');
		for (int i = 0; i < expression.length(); i++) {
			char ops = expression.charAt(i);
			if (ops == '+' || ops == '-')
				sign.add(ops);
		}
		int preNum = 0, preDen = 1, i = 0;
		for (String sub : expression.split("(\\+)|(-)")) {
			if (sub.length() > 0) {
				String[] fraction = sub.split("/");
				int num = (Integer.parseInt(fraction[0]));
				int den = (Integer.parseInt(fraction[1]));
				int gcd = Math.abs(gcd(den, preDen));
				
				if (sign.get(i++) == '+')	// 计算分子
					preNum = (preNum * den + num * preDen) / gcd;
				else
					preNum = (preNum * den - num * preDen) / gcd;
				preDen = den * preDen / gcd;	// 计算分母
				gcd = Math.abs(gcd(preDen, preNum));		// 对自己约分
				preNum /= gcd;
				preDen /= gcd;
			}
		}
		return preNum + "/" + preDen;
	}
	
	/** Greatest Common Divisor 得到最大公约数 */
	
	public int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
