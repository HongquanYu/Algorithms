package comparison;

/** @author: Hongquan Yu
 *  @date: Feb 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** Space: N, Time: N*lgN, Worst: N*lgN */

public class HeapSort {

	public void sort(int arr[]) {
		int n = arr.length;

		for (int i = n / 2 - 1; i >= 0; i--)			// 建堆
			heapAdjust(arr, n, i);
		// 	heapify(arr, n, i);

		for (int i = n - 1; i >= 0; i--) {			// 排序
			// 把大顶堆的根节点放在最后
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			heapify(arr, i, 0);		// 由于有交换打乱了堆，子堆进行调整
		}
	}

	/** Recursion 建堆： 对索引为i的子树建堆，n是数组的长度 */
	private void heapify(int arr[], int n, int i) {
		int largest = i; 		// 假设根是最大值
		int l = 2 * i + 1, r = 2 * i + 2;
		
		// 找到左右孩子中较大的那个
		if (l < n && arr[l] > arr[largest])
			largest = l;
		if (r < n && arr[r] > arr[largest])
			largest = r;
		
		// 根节点不是最大值，那么需要交换
		if (largest != i) {				// 根不是最大，需要和最大的孩子交换
			int tmp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = tmp;

			heapify(arr, n, largest);		// 迭代对subtree进行建堆
		}
	}
	
	/** Iterative 建堆 */
	
	private void heapAdjust(int[] a, int N, int i) {
		int subroot = a[i];
		int maxOfChild = 2 * i + 1;
		
		while (maxOfChild < N) {
			if (maxOfChild + 1 < N && a[maxOfChild] < a[maxOfChild + 1])
				++maxOfChild;
			if (a[i] < a[maxOfChild]) {	// 根节点比孩子小
				a[i] = a[maxOfChild];
				i = maxOfChild;
				maxOfChild = 2 * i + 1;
			} else		// 根节点比孩子大，不再需要调整
				break;
			a[i] = subroot;
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[]) {
		int arr[] = {12, 11, 13, 5, 6, 7, 1, -3, 100, 45, 33};

		HeapSort ob = new HeapSort();
		ob.sort(arr);

		System.out.println("Sorted array is: ");
		printArray(arr);
	}
}
