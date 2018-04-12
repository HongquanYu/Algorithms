package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StudentAttendanceRecordI_551 {
	public boolean checkRecord(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == 'A')
				count++;
		return count < 2 && s.indexOf("LLL") < 0;
	}
	
	public boolean checkRecord2(String s) {
		int count = 0;
		for (int i = 0; i < s.length() && count < 2; i++)
			if (s.charAt(i) == 'A')
				count++;
		return count < 2 && s.indexOf("LLL") < 0;
	}
	
	/***/
	
	public boolean checkRecord3(String s) {
		int absent = 0;
		boolean noTwoLate = true;

		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == 'A')
				absent++;
			else if (s.charAt(i) == 'L') {
				if (i - 1 >= 0 && i + 1 < s.length() && s.charAt(i - 1) == 'L' && s.charAt(i + 1) == 'L')
					noTwoLate = false;
			}
		}

		return (absent <= 1 && noTwoLate) ? true : false;
	}
}
