package ArrayString;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class StringRotation_9 {
	
	// 使用一次isSubstring来确定s2是不是s1的rotation
	public boolean isRotation(String s1, String s2) {
		int n1 = s1.length();
		
		if (n1 == s2.length() && n1 > 0) {
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}
	
	// 下面这个方法是虚拟的，只是提供调用方便
	private boolean isSubstring(String s, String q) {
		return true;
	}
}
