package array;

/** @author: Hongquan Yu
 *  @date: Apr 3, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class FirstMissingPositive_41 {
	
	/** 无序数组的题目线性解法往往要hashmap，但是这题要常数空间。将数组本身看作一个 hashmap，存A[i - 1] = i.
	 * 目标是尽可能将数字 i 放在第 i - 1 个位置。
	 * 下面为什么要用 A[i] != A[A[i] - 1] 这个式子？
	 * 1，如果满足条件，A[i - 1] = i，这个式子就变成A[i] != A[i]，显然不满足交换条件
	 * 2，若不满足，则索引 A[i] - 1 才是该存储当前元素的位置，我们要将其交换到那个位置去。
	 * 综合上述，我们可以使用这个式子来做调整。  */
	
	public int firstMissingPositive(int[] A) {
		int N = A.length;
		for (int i = 0; i < N; i++) {
			while (A[i] > 0 && A[i] <= N && A[i] != A[A[i] - 1])
				swap(A, i, A[i] - 1);
		}
		
		for (int i = 0; i < N; i++)
			if (A[i] != i + 1)
				return i + 1;
		
		return N + 1;
	}

	private void swap(int[] A, int i, int j) {
		A[i] ^= A[j];
		A[j] ^= A[i];
		A[i] ^= A[j];
	}
	
	public static void main(String[] args) {
		int[] i1 = new int[] {1,2,0};
		int[] i2 = new int[] {3,4,-1,1};
		int[] i3 = new int[] {3,4,-1,7};
		
		FirstMissingPositive_41 f = new FirstMissingPositive_41();
		System.out.println(f.firstMissingPositive(i1));
		System.out.println(f.firstMissingPositive(i2));
		System.out.println(f.firstMissingPositive(i3));
	}
}
