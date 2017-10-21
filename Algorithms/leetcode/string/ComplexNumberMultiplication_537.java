package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ComplexNumberMultiplication_537 {
	public String complexNumberMultiply(String a, String b) {
		String x[] = a.split("\\+|i");
		String y[] = b.split("\\+|i");
		int a_real = Integer.parseInt(x[0]);
		int a_img = Integer.parseInt(x[1]);
		int b_real = Integer.parseInt(y[0]);
		int b_img = Integer.parseInt(y[1]);
		return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real) + "i";
	}
}
