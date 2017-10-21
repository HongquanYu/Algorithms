package bitmanipulation;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class UTF8Validation_393 {
	public boolean validUtf8(int[] data) {
		int count = 0;
		for (int c : data) {
			if (count == 0) {
				if ((c >> 5) == 0b110)
					count = 1;
				else if ((c >> 4) == 0b1110)
					count = 2;
				else if ((c >> 3) == 0b11110)
					count = 3;
				else if ((c >> 7) != 0)
					return false;
			} else {
				if ((c >> 6) != 0b10)
					return false;
				count--;
			}
		}
		return count == 0;
	}
	
	
	public boolean validUtf8_2(int[] data) {
		if (data == null || data.length == 0)
			return false;
		boolean isValid = true;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > 255)
				return false; // 1 after 8th digit, 100000000
			int numberOfBytes = 0;
			if ((data[i] & 128) == 0) { // 0xxxxxxx, 1 byte, 128(10000000)
				numberOfBytes = 1;
			} else if ((data[i] & 224) == 192) { // 110xxxxx, 2 bytes, 224(11100000), 192(11000000)
				numberOfBytes = 2;
			} else if ((data[i] & 240) == 224) { // 1110xxxx, 3 bytes, 240(11110000), 224(11100000)
				numberOfBytes = 3;
			} else if ((data[i] & 248) == 240) { // 11110xxx, 4 bytes, 248(11111000), 240(11110000)
				numberOfBytes = 4;
			} else {
				return false;
			}
			for (int j = 1; j < numberOfBytes; j++) { // check that the next n bytes start with
														// 10xxxxxx
				if (i + j >= data.length)
					return false;
				if ((data[i + j] & 192) != 128)
					return false; // 192(11000000), 128(10000000)
			}
			i = i + numberOfBytes - 1;
		}
		return isValid;
	}
}
