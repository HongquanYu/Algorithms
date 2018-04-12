package google;

/** @author: Hongquan Yu
 *  @date: Jan 20, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CompareCharArrays {
	public static boolean compare(char[] a1, char[] a2) {
		if (a1 == null && a2 == null || (a1.length == 0 && a2.length == 0))
			return true;
		if (a1 == null || a2 == null || a1.length == 0 || a2.length == 0)
			return false;
		
		int n1 = a1.length - 1, n2 = a2.length - 1;
		int cnt1 = 0, cnt2 = 0;
		
		while (n1 >= 0 || n2 >= 0) {
			if (n1 < 0) {
			}
			
			if (a1[n1] != 'X' && a2[n2] != 'X') {	// 同为字符， 判断是否
				if (cnt1 == 0 && cnt2 == 0) {	// 都没有退格
					if (a1[n1] != a2[n2]) 	return false;
					n1--;	n2--;
				} else if (cnt1 != 0) {			// 第一个有退格
					n1--; 	cnt1--;
				} else if (cnt2 != 0) {			// 第二个有退格
					n2--; 	cnt2--;
				} else {							// 都有退格
					n1--; 	n2--;
					cnt1--; 	cnt2--;
				}
			} else if (a1[n1] == 'X') {				// 第一个是退格
				cnt1++; 		n1--;
			} else if (a2[n2] == 'X') {				// 第二个是退格
				cnt2++; 		n2--;
			} else {									// 都是退格
				cnt1++; 	cnt2++;
				n1--;	n2--;
			}
		}

		return n1 <= 0 && n2 <= 0;
	}
	
	private static boolean isValid(char[] a, int idx) {
		int cnt = 0, c = 0;
		
		while(idx >= 0) {
			if (a[idx] == 'X')
				cnt++;
			else
				c++;
		}
		
		return false;
	}
	
	public static void main(String [] args) {
		char[] a1 = new char[] {'a', 'b', 'X', 'd', 'c'};
		char[] a2 = new char[] {'a', 'd', 'c'};
		
		char[] a3 = new char[] {'a', 'b', 'X', 'X', 'X', 'd', 'c'};
		char[] a4 = new char[] {'d', 'c'};
		
		char[] a5 = new char[] {'a', 'b', 'd', 'X'};
		char[] a6 = new char[] {'a', 'd'};
		
		char[] a7 = new char[] {'X', 'X', 'X', 'X'};
		char[] a8 = new char[] {'X', 'X'};
		
		char[] a9 = new char[] {'d', 'g', 'j', 'a', 'b', 'X', 'X', 'X', 'd', 'c'};
		char[] a10 = new char[] {'d', 'c'};
		
		System.out.println("a1, a2 " + compare(a1, a2));
		System.out.println("a3, a4 " + compare(a3, a4));
		System.out.println("a5, a6 " + compare(a5, a6));
		
		System.out.println("a6, a5 " + compare(a6, a5));
		System.out.println("a2, a1 " + compare(a2, a1));
		System.out.println("a4, a3 " + compare(a4, a3));
		System.out.println("a7, a8 " + compare(a7, a8));	
		System.out.println("a9, a10 " + compare(a9, a10));	
	}
}
