package memoryManagement;

/** @author: Hongquan Yu
 *  @date: Feb 17, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
//Java implementation of First - Fit algorithm

public class FirstFit {
	static void firstFit(int blockSize[], int m, int processSize[], int n) {
		int allocation[] = new int[n];		// block id of the block allocated to a process

		for (int i = 0; i < allocation.length; i++)	// Initially no block is assigned to any process
			allocation[i] = -1;

		// pick each process and find suitable blocks according to its size ad assign to it
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (blockSize[j] >= processSize[i]) {
					allocation[i] = j;		// allocate block j to p[i] process
					blockSize[j] -= processSize[i];	// Reduce available memory in this block.

					break;
				}
			}
		}

		System.out.println("\nProcess No.\tProcess Size\tBlock no.");
		for (int i = 0; i < n; i++) {
			System.out.print("   " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
			if (allocation[i] != -1)
				System.out.print(allocation[i] + 1);
			else
				System.out.print("Not Allocated");
			System.out.println();
		}
	}

	// Driver Method
	public static void main(String[] args) {
		int blockSize[] = {100, 500, 200, 300, 600};
		int processSize[] = {212, 417, 112, 426};
		int m = blockSize.length;
		int n = processSize.length;

		firstFit(blockSize, m, processSize, n);
	}
}
