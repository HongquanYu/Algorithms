package array;

/* The intuition is to see if current digit is 9, if it is, then there is a carry to next digit;
 * If not, then we can just add 1 to current digit and return this array.
 */

public class PlusOne_66 {
	public int[] plusOne(int[] digits) {

		int n = digits.length;
		
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}

			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;

		return newNumber;
	}
}
