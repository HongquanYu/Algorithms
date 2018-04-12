package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SurroundedRegions_130 {
	
	/** DFS 解法
	 * 首先检查 borders 有没有 'O'，有的话将它和邻居都转化成 '1'。
	 * 然后遍历 board 将所有的 'O' 都转化成 'X'
	 * 然后将所有的 '1' 转化成 'O' */
	
	public void solve(char[][] board) {
		if (board == null || board.length == 0)
			return;
		int i, j, N = board.length, M = board[0].length;

		for (i = 0; i < N; i++) {	// 检查第一列和最后一列
			dfs(board, i, 0, N, M);
			if (M > 1)
				dfs(board, i, M - 1, N, M);
		}
		for (j = 1; j + 1 < M; j++) {	// 检查第一行和最后一行
			dfs(board, 0, j, N, M);
			if (N > 1)
				dfs(board, N - 1, j, N, M);
		}
		for (i = 0; i < N; i++)		// 将所有的 'O' 都转化成 'X'
			for (j = 0; j < M; j++)
				if (board[i][j] == 'O')
					board[i][j] = 'X';
		for (i = 0; i < N; i++)		// 将所有的 '1' 转化成 'O'
			for (j = 0; j < M; j++)
				if (board[i][j] == '1')
					board[i][j] = 'O';
	}
	
	/** DFS将所有的相邻边界的 'O' 都转化成 '1'。 */
	
	private void dfs(char[][] board, int i, int j, int N, int M) {
		if (board[i][j] == 'O') {
			board[i][j] = '1';
			if (i > 1) 		dfs(board, i - 1, j, N, M);
			if (j > 1) 		dfs(board, i, j - 1, N, M);
			if (i + 1 < N)	dfs(board, i + 1, j, N, M);
			if (j + 1 < M)	dfs(board, i, j + 1, N, M);
		}
	}
	
	/*************************** Union Find Solution **************************/
	
	private int[] ID; // union find set
	private boolean[] hasEdgeO; // whether an union has an 'O' which is on the edge of the matrix

	public void solve1(char[][] board) {
		if (board.length == 0 || board[0].length == 0)
			return;

		// init, every char itself is an union
		int N = board.length, M = board[0].length, LEN = N * M;
		ID = new int[LEN];
		hasEdgeO = new boolean[LEN];
		for (int i = 0; i < LEN; i++)
			ID[i] = i;
		for (int i = 0; i < LEN; i++) {
			int x = i / M, y = i % M;
			hasEdgeO[i] = (board[x][y] == 'O' && (x == 0 || x == N - 1 || y == 0 || y == M - 1));
		}

		/* iterate the matrix, for each char, union it + its upper char + its
		* right char if they equals to each other */
		for (int i = 0; i < LEN; i++) {
			int x = i / M, y = i % M, up = x - 1, right = y + 1;
			if (up >= 0 && board[x][y] == board[up][y])
				union(i, i - M);
			if (right < M && board[x][y] == board[x][right])
				union(i, i + 1);
		}

		// for each char in the matrix, if it is an 'O' and its union doesn't
		// has an 'edge O', the whole union should be setted as 'X'
		for (int i = 0; i < LEN; i++) {
			int x = i / M, y = i % M;
			if (board[x][y] == 'O' && !hasEdgeO[findRoot(i)])
				board[x][y] = 'X';
		}
	}

	private void union(int x, int y) {
		int rootX = findRoot(x);
		int rootY = findRoot(y);
		// if there is an union has an 'edge O',the union after merge should be marked too
		ID[rootX] = rootY;
		this.hasEdgeO[rootY] = this.hasEdgeO[rootX] || this.hasEdgeO[rootY];
	}

	private int findRoot(int x) {
		while (x != ID[x]) {
			x = ID[x];
			ID[x] = ID[ID[x]];
		}
		return ID[x];
	}
}
