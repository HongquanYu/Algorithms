import edu.princeton.cs.algs4.StdOut;

public class QuickSort {
	public static void sort(int[] a) {
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(int[] a, int l, int h) {
		if (h <= l)	return;
		int p = partition(a, l, h);
		sort(a, l, p - 1);
		sort(a, p + 1, h);
	}
	
	private static int partition(int[] a, int l, int h) {
		int i = l, j = h + 1;
		int v = a[l];
		
		while (true) {
			while (less(a[++i], v))
				if (i == h)
					break;
			while (less(v, a[--j]))
				if (j == l)
					break;
			if (i >= j)	// check for cross.
				break;
			exch(a, i, j);
		}
		exch(a, l, j);
		return j;
	}
	
	private static boolean less(int v, int w) {
		return v < w;
	}
	
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + ", ");
        }
    }
	
	public static void main(String[] args) {
		int[] ar = {23, 94, 236, 2836, 1, 139, 8, 7286, 28392873};
		sort(ar);
		show(ar);
	}
}
