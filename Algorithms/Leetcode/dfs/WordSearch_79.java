package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class WordSearch_79 {
	
	/** DFS Solution */
	
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (exist(board, i, j, word, 0))
					return true;
		
		return false;
	}
	
	private boolean exist(char[][] board, int i, int j, String word, int ith){
		if (ith == word.length()) 
			return true;
		// 索引的边界检查，或者 matrix 里当前字符 和 字符串里 当前字符 不符！
		if (i > board.length - 1 || i < 0 || j < 0 || j > board[0].length - 1 || board[i][j] != word.charAt(ith))
			return false;
		
		board[i][j] = '*';		// Mark it in case we form a cycle
	    boolean result =    exist(board, i - 1, j, word, ith + 1) ||
	                        exist(board, i, j - 1, word, ith + 1) ||
	                        exist(board, i, j + 1, word, ith + 1) ||
	                        exist(board, i + 1, j, word, ith + 1);
	    
	    board[i][j] = word.charAt(ith);  // 检查结果已经知道了，如果是false，为了不影响后面的搜索，我们得改回来
	    
	    return result;
	}
}
