package string;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReverseStringII_541 {
	public String reverseStr(String s, int k) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		int i = 0;
		while (i < n) {
			int j = Math.min(i + k - 1, n - 1);
			swap(arr, i, j);
			i += 2 * k;
		}
		return String.valueOf(arr);
	}

	private void swap(char[] arr, int l, int r) {
		while (l < r) {
			char temp = arr[l];
			arr[l++] = arr[r];
			arr[r--] = temp;
		}
	}
}
