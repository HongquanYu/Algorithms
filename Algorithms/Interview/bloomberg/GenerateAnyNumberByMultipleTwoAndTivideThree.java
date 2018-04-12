package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class GenerateAnyNumberByMultipleTwoAndTivideThree {
	private int target;
	
	public int generate(int num) {
		int base = 1;
		target = num * 3;
		
		while (!isValid(base)) {
			while (base < 3 * num) {
				base *= 2;
				if (isValid(base))	break;
			}
			while (!isValid(base) && base > 3 * num + 2)	{
				base /= 3;
				if (isValid(base))	break;
			}
		}
		
		return base / 3;
	}
	
	private boolean isValid(int num) {
		return num == target || num == target + 1 || num == target + 2;
	}
	
	public static void main(String[] args) {
		GenerateAnyNumberByMultipleTwoAndTivideThree g = new GenerateAnyNumberByMultipleTwoAndTivideThree();
		
		System.out.println(g.generate(19));
	}
}
