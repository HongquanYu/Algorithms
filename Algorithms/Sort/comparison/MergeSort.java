package comparison;

/**
 * @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: N, Time: N*lgN, Worst: N*lgN */

public class MergeSort {
	public int[] sort(int[] a) {
		int[] aux = new int[a.length];
		return sort(a, aux, 0, a.length - 1);
	}

	private int[] sort(int[] a, int[] aux, int l, int h) {
		if (h <= l)
			return null;
		
		int m = l + (h - l) / 2;
		
		sort(a, aux, l, m);
		sort(a, aux, m + 1, h);
		return merge(a, aux, l, m, h);
	}
	
	private int[] merge (int[] a, int[] aux, int l, int m, int h) {
		for (int p = l; p <= h; ++p)
			aux[p] = a[p];
		
		int i = l, j = m + 1;
		for (int k = l; k <= h; ++k) {
			if (i > m)					a[k] = aux[j++];
			else if (j > h)				a[k] = aux[i++];
			else if (aux[i] < aux[j])	a[k] = aux[i++];
			else							a[k] = aux[j++];
		}
		return a;
	}
	
	/** MergeSort Bottom Up */
	
	public void sortBU(int[] a) {
		int n = a.length;
		int[] aux = new int[n];

		for (int len = 1; len < n; len *= 2) {
			for (int lo = 0; lo < n - len; lo += len + len) {
				int mid = lo + len - 1;
				int hi = Math.min(lo + len + len - 1, n - 1);
				merge(a, aux, lo, mid, hi);
			}
		}
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
        		System.out.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		
		int[] ar = {23, 94, 236, 2836, 1, 139, 8, 7286, 28392873};
		ms.sortBU(ar);
		show(ar);
		System.out.println();
		int[] a2 = {23, 94, 236, 2836, 1, 139, 8, 7286, 28392873};
		ms.show(ms.sort(a2));
	}
}
