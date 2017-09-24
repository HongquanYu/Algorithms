/*
 * Add using bitwise operation, multiply using shift-and-add
 * time complexity: o(n2)
 */
public class MultiplyWithoutArithmeticOperators {
	
	/*
	 * initialize the result to 0 and iterate through the bits of x, 
	 * adding y*2^k to the result if the kth bit of x is 1.
	 */
	public static long multiply(long x, long y) {
		long sum = 0;
		while (x != 0) {
			if ((x & 1) != 0)	sum = getSum1(sum, y);
			x >>>= 1;
			y <<= 1;	//compute y*2^k.
		}
		return sum;
	}
	
	/*
	 * do the add job bit-by-bit, it is a little complicated.
	 * the simple version is in below named getSum1();
	 */
	private static long add(long a, long b) {
		long sum = 0;		// result to be return
		long carryin = 0;	// carry
		long k = 1;			// auxiliary bit to check current bit value
		long tempA = a;
		long tempB = b;
		while (tempA != 0 || tempB != 0) {	// while a and b are not empty
			long ak = a & k;				// ak is the bit value of current bit of number a, (0 or 1)
			long bk = b & k;				// bk is the bit value of current bit of number b, (0 or 1)
			
			//carryout checks if the adding result of current bit carrys
			long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
			sum |= (ak ^ bk ^ carryin); 	//the only condition that the equation == 1 is there is only ONE '1' 
			carryin = carryout << 1;		//update the carryin for the computation of next bit
			k <<= 1;
			tempA >>>= 1;
			tempB >>>= 1;
		}
		return sum | carryin;
	}
	
    public static long getSum1(long a, long b) {
    	if (a == 0) return b;
    	if (b == 0) return a;

    	while (b != 0) {
    		long carry = a & b;
    		a = a ^ b;
    		b = carry << 1;
    	}
    	
    	return a;
    }
	
	public static void main(String[] args) {
		MultiplyWithoutArithmeticOperators mwao = new MultiplyWithoutArithmeticOperators();
		System.out.println(mwao.multiply(143, 10));
	}
}
