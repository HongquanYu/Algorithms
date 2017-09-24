package dp;

import java.util.HashMap;

public class UglyNumber2_264 {
	
	/* The idea is use a sequence of natural number to multiply 2, 3 and 5. Then we
	 * get three sequences of ugly numbers, we merge them increasingly by using merge
	 * algorithm from merge sort. Then we get a sequence of full ugly number set.
	 */
	
	public int nthUglyNumber(int n) {
		
		if (n <= 0)					// get rid of corner cases
			return -1; 
		
		int p2 = 0, p3 = 0, p5 = 0; // pointers for 2, 3, 5
		
		int[] k = new int[n];
		k[0] = 1;
		
		for (int i = 1; i < n; i++) {
			k[i] = Math.min(k[p2] * 2, Math.min(k[p3] * 3, k[p5] * 5));	// every time we get one ugly #
			
			if (k[i] == k[p2] * 2) p2++;	// move the pointer which has been used
			if (k[i] == k[p3] * 3) p3++;
			if (k[i] == k[p5] * 5) p5++;
		}
		
		return k[n - 1];
	}
	
	public int nthUglyNumber2(int n) {
		HashMap<Integer, Integer> map = new HashMap<>(3000);	// nth - value
		int idx = 2, np = 2;
		map.put(1, 1);	// special case 1 - 1.
		
		while (idx <= n) {
			if (isUgly(np++, map))
				map.put(idx++, np - 1);
		}
		
		return map.get(n);
	}
	
	private boolean isUgly(int num, HashMap<Integer, Integer> map) {
		if (num <= 0)
			return false;
		
		if (map.containsValue(num))
			return true;
		
		while (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) {
			if (map.containsValue(num))
				return true;
			num = (num % 2 == 0) ? num /= 2 : num;
			num = (num % 3 == 0) ? num /= 3 : num;
			num = (num % 5 == 0) ? num /= 5 : num;
		}
		
		return num == 1;	
	}
}
