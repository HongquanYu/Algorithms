package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseMatrixMultiplication_311 {
	
	public int[][] multiply2(int[][] A, int[][] B) {
		if (A == null || B == null)
			return null;
		if (A[0].length != B.length)
			throw new IllegalArgumentException(
					"A's column number must be equal to B's row number.");
		Map<Integer, HashMap<Integer, Integer>> tableA = new HashMap<>();
		Map<Integer, HashMap<Integer, Integer>> tableB = new HashMap<>();
		int[][] C = new int[A.length][B[0].length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (A[i][j] != 0) {
					if (tableA.get(i) == null)
						tableA.put(i, new HashMap<Integer, Integer>());
					tableA.get(i).put(j, A[i][j]);
				}
			}
		}

		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[i].length; j++) {
				if (B[i][j] != 0) {
					if (tableB.get(i) == null)
						tableB.put(i, new HashMap<Integer, Integer>());
					tableB.get(i).put(j, B[i][j]);
				}
			}
		}

		for (Integer i : tableA.keySet()) {
			for (Integer k : tableA.get(i).keySet()) {
				if (!tableB.containsKey(k))
					continue;
				for (Integer j : tableB.get(k).keySet()) {
					C[i][j] += tableA.get(i).get(k) * tableB.get(k).get(j);
				}
			}
		}
		return C;
	}
	
	
	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] C = new int[m][nB];

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < nB; j++) {
						if (B[k][j] != 0)
							C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;
	}
	
	public int[][] multiply1(int[][] A, int[][] B) {
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] result = new int[m][nB];

		List[] indexA = new List[m];
		for (int i = 0; i < m; i++) {
			List<Integer> numsA = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (A[i][j] != 0) {
					numsA.add(j);
					numsA.add(A[i][j]);
				}
			}
			indexA[i] = numsA;
		}

		for (int i = 0; i < m; i++) {
			List<Integer> numsA = indexA[i];
			for (int p = 0; p < numsA.size() - 1; p += 2) {
				int colA = numsA.get(p);
				int valA = numsA.get(p + 1);
				for (int j = 0; j < nB; j++) {
					int valB = B[colA][j];
					result[i][j] += valA * valB;
				}
			}
		}

		return result;
	}
}
