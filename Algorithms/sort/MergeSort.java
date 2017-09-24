import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeSort<Item> {
	public static void sort(Comparable[] cmp) {
		Comparable [] aux = new Comparable[cmp.length];
		sort(cmp, aux, 0, cmp.length - 1);
	}
	
	private static void sort(Comparable [] a, Comparable[] aux, int lo, int hi) {
		if (lo >= hi) return;
		int mid = lo + (lo+hi) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	private static void merge(Comparable [] a, Comparable [] aux, int l, int mid, int h) {
		for (int i = 0; i < a.length; ++i) {
			aux[i] = a[i];
		}
		
		int i = l, j = mid;
		for (int k = l; k <= h; ++k) {
			if (i > mid)				a[k] = aux[j++];
			else if (j > h)				a[k] = aux[i++];
			else if (aux[i].compareTo(aux[i]) < 0) 	a[k] = aux[i++];
			else						a[k] = aux[j++];
		}
	}
	
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }
	
//	public int compareTo(Item o) {
//		
//		return 0;
//	}
	
}
