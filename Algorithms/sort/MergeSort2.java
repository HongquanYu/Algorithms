import edu.princeton.cs.algs4.StdOut;

public class MergeSort2 {
	public static int[] sort (int[] a) {
		int[] aux = new int [a.length];
		return sort(a, aux, 0, a.length - 1);
	}
	
	private static int[] sort (int[] a, int[] aux, int l, int h) {
		if (h <= l)	return null;
		int m = l + (h - l) / 2;
		sort(a, aux, l, m);
		sort(a, aux, m+1, h);
		return merge(a, aux, l, m, h);
	}
	
	private static int[] merge (int[] a, int[] aux, int l, int m, int h) {
		for (int p = l; p <= h; ++p)
			aux[p] = a[p];
		
		int i = l, j = m + 1;
		for (int k = l; k <= h; ++k) {
			if (i > m)					a[k] = aux[j++];
			else if (j > h)				a[k] = aux[i++];
			else if (aux[i] < aux[j])	a[k] = aux[i++];
			else						a[k] = aux[j++];
		}
		return a;
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		int[] ar = {23, 94, 236, 2836, 1, 139, 8, 7286, 28392873};
		show(sort(ar));
	}
}
