package string;

import java.util.Arrays;

public class LongestAbsoluteFilePath_388 {
	
	/** stack 数组用来记录每一层的路径 */
	
	public static int lengthLongestPath(String input) {
		String[] paths = input.split("\n");
		System.out.println(Arrays.toString(paths));
		int[] stack = new int[paths.length + 1];
		int maxLen = 0;
		
		for (String dir : paths) {
			int lev = dir.lastIndexOf("\t") + 1;	// 找到属于第几层
			int curLen = stack[lev + 1] = stack[lev] + dir.length() - lev + 1;
			if (dir.contains("."))
				maxLen = Math.max(maxLen, curLen - 1);
		}
		return maxLen;
	}

	public static void main(String[] args) {
		String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		System.out.println(lengthLongestPath(input));
	}
}
