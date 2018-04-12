package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 29, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class URLify_3 {
	public static String URLify(char[] string, int len) {
		int count = 0, index;
		
		for (int i = 0; i < len; ++i)
			if (string[i] == ' ')
				count++;
		
		index = len + count * 2;
		
		if (len < string.length)
			string[len] = '\0';
		for (int k = len - 1; k >= 0; --k) {
			if (string[k] == ' ') {
				string[index - 1] = '0';
				string[index - 2] = '2';
				string[index - 3] = '%';
				index -= 3;
			} else {
				string[index - 1] = string[k];
				index--;
			}
		}
		
		return "";
	}
	
	public static void main(String[] args) {
		char[] t = new char[20];
		
		t[0] = 'M';
		t[1] = 'r';
		t[2] = ' ';
		t[3] = 'J';
		t[4] = 'o';
		t[5] = 'h';
		t[6] = 'n';
		t[7] = ' ';
		t[8] = 'S';
		t[9] = 'm';
		t[10] = 'i';
		t[11] = 't';
		t[12] = 'h';
		
		URLify(t, 13);
		System.out.println(t);
	}
}
