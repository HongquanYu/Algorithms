package bloomberg;

/** @author: Hongquan Yu
 *  @date: Feb 22, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ValidSixDigitsInteger {
	public int count(int[] nums) {
		int res = 0;
		for (int i : nums) {
			if (isValid(i))
				res++;
		}
		
		return res;
	}
	
	private boolean isValid(int i) {
		int len = String.valueOf(i).length();
		if (len != 6)
			return false;
		
		String s = String.valueOf(i);
		int sum = 0;
		for (int j = 0; j < s.length(); ++j) {
			if (j < 3)
				sum += s.charAt(j) - '0';
			else
				sum -= s.charAt(j) - '0';
		}
		
		return sum == 0;
	}
	
	public static void main(String[] args) {
		ValidSixDigitsInteger v = new ValidSixDigitsInteger();
		int[] temp = new int[] {123420, 567765, 987543, 000111, 101010, -123321};
		
		System.out.println(v.count(temp));
	}
}
