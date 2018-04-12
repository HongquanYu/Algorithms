package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReadNCharactersGivenRead4II_CallMultipleTimes_158 {
	private int buffPtr = 0;
	private int buffCnt = 0;
	private char[] buff = new char[4];

	public int read(char[] buf, int n) {
		int ptr = 0;
		while (ptr < n) {
			if (buffPtr == 0) {
				buffCnt = read4(buff);
			}
			if (buffCnt == 0)
				break;
			while (ptr < n && buffPtr < buffCnt) {
				buf[ptr++] = buff[buffPtr++];
			}
			if (buffPtr >= buffCnt)
				buffPtr = 0;
		}
		return ptr;
	}
	
	// The read4 API is defined in the parent class Reader4.
	private int read4(char[] buf) {
		return 0;
	}
}
