package hashtable;

import java.util.HashSet;

public class ValidSudoku_36 {
	public boolean isValidSudoku(char[][] board) {
		boolean[][] used1 = new boolean[9][9];			// Check row: 9 rows with col elements every row
		boolean[][] used2 = new boolean[9][9];			// Check col: 9 cols with row elements every col
		boolean[][] used3 = new boolean[9][9];			// Check sub-box: 9 box with 9 elements in each box

		for (int i = 0; i < 9; ++i)
			for (int j = 0; j < 9; ++j)
				if (board[i][j] != '.') {		// If it is a number cell
					int num = board[i][j] - '0' - 1;		// Index of the actual number 
					int k = i / 3 * 3 + j / 3;			// Mapping 9*9 grid to 3*3 grid
					
					if (used1[i][num] || used2[j][num] || used3[k][num])
						return false;
					used1[i][num] = used2[j][num] = used3[k][num] = true;		// Mark used cells
				}

		return true;
	}
	
	public boolean isValidSudoku2(char[][] board) {
		
		for (int i = 0; i < 9; i++) {
			HashSet<Character> rows 		= new HashSet<Character>();
			HashSet<Character> columns 	= new HashSet<Character>();
			HashSet<Character> cube 		= new HashSet<Character>();
			
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.' && !rows.add(board[i][j]))
					return false;
				if (board[j][i] != '.' && !columns.add(board[j][i]))
					return false;
				int RowIndex = 3 * (i / 3);
				int ColIndex = 3 * (i % 3);
				if (board[RowIndex + j / 3][ColIndex + j % 3] != '.' && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
					return false;
			}
		}
		
		return true;
	}
}
