package binarySearch;

/** @author: Hongquan Yu
 *  @date: Jan 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindSmallestLetterGreaterThanTarget_744 {
    public char nextGreatestLetter(char[] a, char x) {
        int lo = 0, hi = a.length;	// hi 比最大的 index 大一个

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > x)     hi = mid;	// 每一次迭代都比最高值大一
            else                lo = mid + 1;
        }

        return a[lo % a.length];		// 最后返回取模
    }
}
