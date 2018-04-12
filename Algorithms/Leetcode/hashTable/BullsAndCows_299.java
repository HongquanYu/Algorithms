package hashTable;

public class BullsAndCows_299 {
	
	/* The idea is to iterate over the numbers in secret and in guess and count all bulls right
	 * away. For cows maintain an array that stores count of the number appearances in secret and in
	 * guess. Increment cows when either number from secret was already seen in guest or vice versa.
	 */
	
	public static String getHint(String secret, String guess) {
		
		int bulls = 0, cows = 0;
		int[] numbers = new int[10];		// 数字在secret和guess里面出现的次数，出现在secret里就加，guess就减
		
		for (int i = 0; i < secret.length(); i++) {
			int s = Character.getNumericValue(secret.charAt(i));
			int g = Character.getNumericValue(guess.charAt(i));
			
			if (s == g) 	bulls++;		// 相同位置相同字符
			else {
				if (numbers[s] < 0) 	cows++;		// guess 串前面出现过这个字符并且目前比secret多，所以应该给予给cow加一
				if (numbers[g] > 0) 	cows++;		// secret 串前面出现过这个字符并且目前比guess多，所以应该给予给cow加一
				numbers[s]++;	// 前面有加上去，应该给予减去已经记过数的
				numbers[g]--;
			}
		}
		
		return bulls + "A" + cows + "B";
	}
	
	public static void main(String[] args) {
		String s = "8734", g = "8543";
		System.out.println(" - " + getHint(s, g));
	}
	
	public String getHint1(String secret, String guess) {
		
	    int bulls = 0, cows = 0;
	    int[] numbers = new int[10];
	    
	    for (int i = 0; i<secret.length(); i++) {
	        if (secret.charAt(i) == guess.charAt(i)) bulls++;
	        else {
	            if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
	            if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
	        }
	    }
	    
	    return bulls + "A" + cows + "B";
	}
}
